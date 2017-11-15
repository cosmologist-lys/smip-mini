package com.smip.controller;

import com.smip.entity.FeedbackJson;
import com.smip.entity.ReqHeadersMsg;
import com.smip.entity.ReqInfoMsg;
import com.smip.entity.sys.Secuser;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.GlobalConstance;
import com.smip.ulities.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by kepler@gmail.com on 2017/11/8.
 */
public class BaseController<T> {
    @Autowired
    private SecuserService secuserService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ModelAttribute("tokenModel")
    public ReqHeadersMsg beforeController(HttpServletRequest request){
        logger.info("tokenModel");
        String username = request.getHeader("username");
        String psw = request.getHeader("psw");
        String encryptedPsw = StringTools.md5(psw);
        Secuser secuser = secuserService.findByName(username);
        ReqHeadersMsg header = new ReqHeadersMsg(false);
        if (null != secuser){
            if (secuser.getPassWord().equals(encryptedPsw))
                header = new ReqHeadersMsg(username,true,psw,encryptedPsw,request.getRequestURI());
        }
        return header;
    }

    public FeedbackJson<T> OK(String describe,T t,ReqHeadersMsg header,String type,int size){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,type);
        return new FeedbackJson(infoMsg,t, HttpStatus.OK,null,size);
    }

    public FeedbackJson<T> FORBIDDEN(ReqHeadersMsg header){
        ReqInfoMsg infoMsg = getInfoMsg(null,header,null);
        return new FeedbackJson(infoMsg,null, HttpStatus.FORBIDDEN,null,0);
    }

    public FeedbackJson<T> NOTFOUND(String describe,ReqHeadersMsg header,String type){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,type);
        return new FeedbackJson(infoMsg,null, HttpStatus.NOT_FOUND,null,0);
    }

    public ReqInfoMsg getInfoMsg(String describe,ReqHeadersMsg header,String type){
        return  new ReqInfoMsg(describe,new Date(),type,header);
    }
}
