package com.smip.controller;

import com.smip.entity.json.FeedbackJson;
import com.smip.entity.json.ReqHeadersMsg;
import com.smip.entity.json.ReqInfoMsg;
import com.smip.entity.sys.Secuser;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.GlobalConstance;
import com.smip.ulities.Q_Cipher;
import com.smip.ulities.Q;
import org.apache.ibatis.javassist.expr.Instanceof;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * controller父类。公共函数beforeController验证合法性
 * 定义OK,FORBIDDEN,NOTFOUND用来返回对应情况下的Feedbackjson对象
 */
public class BaseController<T>{
    @Autowired
    private SecuserService secuserService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ModelAttribute("tokenModel")
    public ReqHeadersMsg beforeController(HttpServletRequest request){
        logger.info("token valid {baseController.beforeController}");
        String username = request.getHeader("username"),
                psw = request.getHeader("psw"),
                alg = request.getHeader("alg");
        //boolean valid = true;
        if (Q.notNull(alg)){
            //// TODO: 2017/11/28
        }
        return (secuserService.validUser(username,psw))?
                    new ReqHeadersMsg(username,true,psw,null,request.getRequestURI())
                    :new ReqHeadersMsg(false);
    }

    /**
     * 返回单个object
     * @return
     */
    public FeedbackJson<T> OK(String describe,T t,ReqHeadersMsg header){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header, null == t?"": t.getClass().getSimpleName());
        if (null != t){
            return new FeedbackJson(infoMsg,t, HttpStatus.OK,null);
        }else{
            return new FeedbackJson(infoMsg,null, HttpStatus.NOT_FOUND,null);
        }
    }
    /**
     * 返回Page<object>
     * @return
     */
    public FeedbackJson<T> OK(String describe, Page<T> ts, ReqHeadersMsg header, int size){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,ts.getClass().getSimpleName());
        if (size > 0)
            return new FeedbackJson(infoMsg,ts,HttpStatus.OK,null,size);
        else
            return new FeedbackJson(infoMsg,ts,HttpStatus.NOT_FOUND,null,size);
    }
    /**
     * 返回int
     * @return
     */
    public FeedbackJson<T> OK(String describe,ReqHeadersMsg header,int count){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,GlobalConstance.JSON_TYPE_INTEGER);
        return new FeedbackJson(infoMsg,count, HttpStatus.OK,null);
    }
    /**
     * 返回boolean
     * @return
     */
    public FeedbackJson<T> OK(String describe,ReqHeadersMsg header,boolean flg){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,GlobalConstance.JSON_TYPE_BOOLEAN);
        return new FeedbackJson(infoMsg,flg, HttpStatus.OK,null);
    }


    public FeedbackJson<T> FORBIDDEN(ReqHeadersMsg header){
        String describe = "没有访问权限";
        ReqInfoMsg infoMsg = getInfoMsg(describe,header,null);
        return new FeedbackJson(infoMsg,null, HttpStatus.FORBIDDEN,null,0);
    }

    public FeedbackJson<T> NOTFOUND(String describe,ReqHeadersMsg header){
        ReqInfoMsg infoMsg = getInfoMsg(describe,header, GlobalConstance.JSON_TYPE_BOOLEAN);
        return new FeedbackJson(infoMsg,null, HttpStatus.NOT_FOUND,null,0);
    }

    public ReqInfoMsg getInfoMsg(String describe,ReqHeadersMsg header,String type){
        header.setPsw(null);
        return new ReqInfoMsg(describe, LocalDate.now().toString()+ " "+LocalTime.now().toString(),type,header);
    }
}
