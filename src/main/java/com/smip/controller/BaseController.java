package com.smip.controller;

import com.smip.entity.json.FeedbackJson;
import com.smip.entity.json.ReqHeadersMsg;
import com.smip.entity.json.ReqInfoMsg;
import com.smip.entity.sys.Secuser;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.CipherTool;
import com.smip.ulities.briefTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * controller父类。公共函数beforeController验证合法性
 * 定义OK,FORBIDDEN,NOTFOUND用来返回对应情况下的Feedbackjson对象
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
        ReqHeadersMsg header = new ReqHeadersMsg(false);
        if (briefTool.notNull(username,psw)){
            String encryptedPsw = CipherTool.md5(psw);
            Secuser secuser = secuserService.findByName(username);
            if (null != secuser){
                if (secuser.getPassWord().equals(encryptedPsw))
                    header = new ReqHeadersMsg(username,true,psw,encryptedPsw,request.getRequestURI());
            }
        }
        return header;
    }

    /**
     * 返回单个object
     * @return
     */
    public FeedbackJson<T> OK(String describe,T t,ReqHeadersMsg header,String type){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,type);
        if (null != t){
            return new FeedbackJson(infoMsg,t, HttpStatus.OK,null);
        }else
            return new FeedbackJson(infoMsg,null, HttpStatus.OK,null);
    }
    /**
     * 返回Page<object>
     * @return
     */
    public FeedbackJson<T> OK(String describe, Page<T> ts, ReqHeadersMsg header, String type, int size){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,type);
        return new FeedbackJson(infoMsg,ts, HttpStatus.OK,null,size);
    }
    /**
     * 返回int
     * @return
     */
    public FeedbackJson<T> OK(String describe,ReqHeadersMsg header,String type,int count){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,type);
        return new FeedbackJson(infoMsg,count, HttpStatus.OK,null);
    }
    /**
     * 返回boolean
     * @return
     */
    public FeedbackJson<T> OK(String describe,ReqHeadersMsg header,String type,boolean flg){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,type);
        return new FeedbackJson(infoMsg,flg, HttpStatus.OK,null);
    }


    public FeedbackJson<T> FORBIDDEN(ReqHeadersMsg header){
        String describe = "没有访问权限";
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,null);
        return new FeedbackJson(infoMsg,null, HttpStatus.FORBIDDEN,null,0);
    }

    public FeedbackJson<T> NOTFOUND(String describe,ReqHeadersMsg header,String type){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,type);
        return new FeedbackJson(infoMsg,null, HttpStatus.NOT_FOUND,null,0);
    }

    public ReqInfoMsg getInfoMsg(String describe,ReqHeadersMsg header,String type){
        header.setPsw(null);
        ReqInfoMsg msg = new ReqInfoMsg(describe,new Date(),type,header);
        return  msg;
    }
}
