package com.smip.controller;

import com.smip.entity.json.*;
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
import java.util.Date;

/**
 * controller父类。公共函数beforeController验证合法性
 * 定义OK,FORBIDDEN,NOTFOUND用来返回对应情况下的Feedbackjson对象
 */
public class BaseController<T> {
    @Autowired
    private SecuserService secuserService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Keycore keycore = new Keycore();
    private Reqmodule reqmodule = new Reqmodule();
    private Respmodule respmodule = new Respmodule();
    private ConJson json = new ConJson();

    @ModelAttribute("tokenModel")
    public ConJson beforeController(HttpServletRequest request) {
        logger.info("token valid {baseController.beforeController}");
        String username = null,
                psw = null,
                request_token = null,
                callback_token = null;
        int auth = 0;
        request_token = request.getHeader("_token");
        //第一次请求_token空,验证用户名密码
        if (!Q.notNull(request_token)) {
            System.out.println("第一次请求");
            username = request.getHeader("username");
            psw = request.getHeader("psw");
            if (Q.notNull(request.getHeader("auth"))) {
                auth = Integer.parseInt(request.getHeader("auth").toString());
            }
            System.out.println(username + psw);
            callback_token = secuserService.validUser(username, psw);
            System.out.println("token=" + callback_token);
            if (!Q.notNull(callback_token)) {
                return new ConJson(false);
            } else {
                return new ConJson(
                        keycore
                                .set_isvalid(true)
                                .set_tkn(username)
                                .set_auth(auth)
                                .set_token(callback_token)
                                .set_comtick(0),
                        reqmodule.setReqtime(new Date())
                                .setUri(request.getRequestURI())
                );
            }
        }
        //非第一次请求，验证token
        else {
            System.out.println("已有token");
            if (secuserService.validToken(request_token)) {
                callback_token = request_token;
                String comtick = request.getHeader("tick");
                int _comtick = 0;
                if (Q.notNull(comtick)) {
                    _comtick = Integer.parseInt(comtick);
                }
                return new ConJson(
                        keycore.set_isvalid(true)
                                .set_tkn(username)
                                .set_auth(auth)
                                .set_token(callback_token)
                                .set_comtick(_comtick),
                        reqmodule.setReqtime(new Date())
                                .setUri(request.getRequestURI())
                );
            } else {
                return new ConJson(false);
            }
        }
    }

    /**
     * 返回单个object
     *
     * @return
     */
    public ConJson<T> OK(String describe, T t, ConJson conJson) {
        return getConjson(describe, t, conJson);
    }

    /**
     * 返回Page<object>
     *
     * @return
     */
    public ConJson<T> OK(String describe, Page<T> paget, ConJson conJson) {
        return getConjson(describe, paget, conJson);
    }

    /**
     * 返回int
     *
     * @return
     */
    public ConJson OK(String describe, int number, ConJson conJson) {
        return getConjson(describe, number, conJson);
    }

    /**
     * 返回boolean
     *
     * @return
     */
    public ConJson OK(String describe, boolean flg, ConJson conJson) {
        return getConjson(describe, flg, conJson);
    }

    public ConJson FORBIDDEN() {
        String _chn = "没有访问权限";
        String _eng = "no permission authorited";
        String describe = _chn + "/" + _eng;
        respmodule = new Respmodule();
        respmodule.setHttpStatus(HttpStatus.FORBIDDEN)
                .setDescribe(describe);
        return new ConJson(keycore,reqmodule,respmodule);
    }

    public ConJson _404() {
        String _chn = "404 未找到指定URL";
        String _eng = "404 NOT FOUND";
        String describe = _chn + "/" + _eng;
        respmodule = new Respmodule();
        respmodule.setHttpStatus(HttpStatus.NOT_FOUND)
                .setDescribe(describe);
        return new ConJson(keycore,reqmodule,respmodule);
    }

    public ConJson getConjson(String describe, Object object, ConJson conJson) {
        int tick = conJson.getKeycore().get_comtick() + 1;
        respmodule.setDescribe(describe)
                .setHttpStatus(HttpStatus.OK)
                .setObject(object)
                .setReptime(new Date());
        conJson.setResponse(respmodule);
        conJson.getKeycore().set_comtick(tick);
        String reqtime = conJson.getRequst().getReqtime();
        String resptime = conJson.getResponse().getReptime();
        if (Q.notNull(reqtime) && Q.notNull(resptime)){
            reqtime = reqtime.replace("/","");
            resptime = resptime.replace("/","");
            double timecost = Double.parseDouble(resptime) -Double.parseDouble(reqtime);
            conJson.getKeycore().set_timecost(timecost);
        }
        conJson.getResponse().setResptype(Q.getClassname(object));
        return conJson;
    }
}
