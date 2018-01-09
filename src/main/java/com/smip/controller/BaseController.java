package com.smip.controller;

import com.smip.entity.json.FeedbackJson;
import com.smip.entity.json.ReqHeadersMsg;
import com.smip.entity.json.ReqInfoMsg;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.GlobalConstance;
import com.smip.ulities.Q;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * controller父类。公共函数beforeController验证合法性
 * 定义OK,FORBIDDEN,NOTFOUND用来返回对应情况下的Feedbackjson对象
 */
public class BaseController<T> {
    @Autowired
    private SecuserService secuserService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ModelAttribute("tokenModel")
    public ReqHeadersMsg beforeController(HttpServletRequest request) {
        logger.info("token valid {baseController.beforeController}");
        String username = null,
                psw = null,
                callback_token = null;
        String request_token = request.getHeader("_token");
        //第一次请求_token空,验证用户名密码
        if (!Q.notNull(request_token) ) {
            username = request.getHeader("username");
            psw = request.getHeader("psw");
            System.out.println(username+psw);
            callback_token = secuserService.validUser(username, psw);
            if (!Q.notNull(callback_token)) {
                return new ReqHeadersMsg(false);
            } else {
                return new ReqHeadersMsg(username, true, callback_token, null, request.getRequestURI());
            }
        }
        //非第一次请求，验证token
        else {
            System.out.println("null");
            if (secuserService.validToken(request_token)) {
                callback_token = request_token;
                return new ReqHeadersMsg(username, true, callback_token, null, request.getRequestURI());
            } else {
                return new ReqHeadersMsg(false);
            }
        }
    }

    /**
     * 返回单个object
     *
     * @return
     */
    public FeedbackJson<T> OK(String describe, T t, ReqHeadersMsg header) {
        ReqInfoMsg infoMsg = getInfoMsg(describe, header, null == t ? "" : t.getClass().getSimpleName());
        if (null != t) {
            return new FeedbackJson(infoMsg, t, HttpStatus.OK, null);
        } else {
            return new FeedbackJson(infoMsg, null, HttpStatus.NOT_FOUND, null);
        }
    }

    /**
     * 返回Page<object>
     *
     * @return
     */
    public FeedbackJson<T> OK(String describe, Page<T> ts, ReqHeadersMsg header, int size) {
        ReqInfoMsg infoMsg = getInfoMsg(describe, header, ts.getClass().getSimpleName());
        if (size > 0)
            return new FeedbackJson(infoMsg, ts, HttpStatus.OK, null, size);
        else
            return new FeedbackJson(infoMsg, ts, HttpStatus.NOT_FOUND, null, size);
    }

    /**
     * 返回int
     *
     * @return
     */
    public FeedbackJson<T> OK(String describe, ReqHeadersMsg header, int count) {
        ReqInfoMsg infoMsg = getInfoMsg(describe, header, GlobalConstance.JSON_TYPE_INTEGER);
        return new FeedbackJson(infoMsg, count, HttpStatus.OK, null);
    }

    /**
     * 返回boolean
     *
     * @return
     */
    public FeedbackJson<T> OK(String describe, ReqHeadersMsg header, boolean flg) {
        ReqInfoMsg infoMsg = getInfoMsg(describe, header, GlobalConstance.JSON_TYPE_BOOLEAN);
        return new FeedbackJson(infoMsg, flg, HttpStatus.OK, null);
    }


    public FeedbackJson<T> FORBIDDEN(ReqHeadersMsg header) {
        String describe = "没有访问权限";
        ReqInfoMsg infoMsg = getInfoMsg(describe, header, null);
        return new FeedbackJson(infoMsg, null, HttpStatus.FORBIDDEN, null, 0);
    }

    public FeedbackJson<T> NOTFOUND(String describe, ReqHeadersMsg header) {
        ReqInfoMsg infoMsg = getInfoMsg(describe, header, GlobalConstance.JSON_TYPE_BOOLEAN);
        return new FeedbackJson(infoMsg, null, HttpStatus.NOT_FOUND, null, 0);
    }

    public ReqInfoMsg getInfoMsg(String describe, ReqHeadersMsg header, String type) {
        //header.setPsw(null);
        return new ReqInfoMsg(describe, LocalDate.now().toString() + " " + LocalTime.now().toString(), type, header);
    }
}
