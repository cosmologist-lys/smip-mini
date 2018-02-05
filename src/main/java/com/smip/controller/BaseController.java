package com.smip.controller;

import com.smip.entity.json.*;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.Q;
import com.smip.ulities.SysConst;
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

    private Keycore keycore = new Keycore();
    private Reqmodule reqmodule = new Reqmodule();
    private Respmodule respmodule = new Respmodule();
    private ConJson json = new ConJson();
    private UserJson userJson = new UserJson();

    @ModelAttribute("tokenModel")
    public ConJson beforeController(HttpServletRequest request) {
        logger.info("token valid {baseController.beforeController}");
        String token = request.getHeader("_token");
        if (!Q.notNull(token)){
            return login(request);
        }else{
            return conns(request,token);
        }
    }

    /**
     * headers={username,psw,auth,unitcode}，第一次请求 = 登陆
     * @param request
     * @return
     */
    public ConJson login(HttpServletRequest request) {
        System.out.println("第一次请求");
        String username = request.getHeader("username");
        String psw = request.getHeader("psw");
        int auth = 0;
        String unitCode = null;
        if (Q.notNull(request.getHeader("auth"))) {
            auth = Integer.parseInt(request.getHeader("auth").toString());
        }
        if (Q.notNull(request.getHeader("unit_code"))) {
            unitCode = request.getHeader("unit_code").toString();
        }
        System.out.println(username + psw);
        userJson = secuserService.validUser(username, psw);
        if (!Q.notNull(userJson)) {
            return new ConJson(false);
        } else {
            String token = userJson.get_token();
            System.out.println("token=" + token);
            json = new ConJson(
                    keycore.set_isvalid(true)
                            .set_tkn(username)
                            .set_auth(auth)
                            .set_token(token)
                            .set_unit_code(unitCode)
                            .set_comtick(0),
                    reqmodule.setReqtime(new Date())
                            .setUri(request.getRequestURI())
            );
            secuserService.saveToken(userJson, json);
            return json;
        }
    }

    /**
     * headers={token,auth} 已经登陆之后发送的request请求
     * conns : connections and communications
     * @param request
     * @param token
     * @return
     */
    public ConJson conns(HttpServletRequest request, String token) {
        System.out.println("已有token");
        if (!secuserService.validToken(token)) {
            return new ConJson(false);
        } else {
            userJson = secuserService.updateToken(
                    token, new ConJson(
                            keycore,
                            reqmodule.setUri(request.getRequestURI())
                                    .setReqtime(new Date()))
            );
            json = new ConJson(
                    keycore.set_isvalid(userJson.is_isvalid())
                            .set_auth(userJson.get_auth())
                            .set_unit_code(userJson.get_unit_code())
                            .set_token(userJson.get_token())
                            .set_comtick(userJson.get_comtick())
                            .set_tkn(userJson.getSecuser().getUserName()),
                    reqmodule.setReqtime(new Date())
                            .setUri(request.getRequestURI())
            );
            return json;
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

    /**
     * 请求的token错误、登陆的密码错误、用户的权限错误时
     * 返回没有权限的提示
     * @return
     */
    public ConJson FORBIDDEN() {
        String _chn = "没有访问权限";
        String _eng = "no permission authorited";
        String describe = _chn + "/" + _eng;
        respmodule = new Respmodule();
        respmodule.setHttpStatus(HttpStatus.FORBIDDEN)
                .setDescribe(describe);
        return new ConJson(keycore, reqmodule, respmodule);
    }

    public ConJson FORBIDDEN(String tag){
        respmodule = new Respmodule();
        respmodule.setHttpStatus(HttpStatus.FORBIDDEN)
                .setDescribe(tag);
        return new ConJson(keycore, reqmodule, respmodule);
    }

    /**
     * 请求了一个不存在的url时返回404
     * todo
     * @return
     */
    public ConJson _404() {
        String _chn = "404 未找到指定URL";
        String _eng = "404 NOT FOUND";
        String describe = _chn + "/" + _eng;
        respmodule = new Respmodule();
        respmodule.setHttpStatus(HttpStatus.NOT_FOUND)
                .setDescribe(describe);
        return new ConJson(keycore, reqmodule, respmodule);
    }

    /**
     * 抽离共用部分。打包conjson
     * @param describe
     * @param object
     * @param conJson
     * @return
     */
    public ConJson getConjson(String describe, Object object, ConJson conJson) {
        respmodule.setDescribe(describe)
                .setHttpStatus(HttpStatus.OK)
                .setObject(object)
                .setReptime(new Date());
        conJson.setResponse(respmodule);
        String reqtime = conJson.getRequst().getReqtime();
        String resptime = conJson.getResponse().getReptime();
        if (Q.notNull(reqtime) && Q.notNull(resptime)) {
            reqtime = reqtime.replace("/", "");
            resptime = resptime.replace("/", "");
            double timecost = Double.parseDouble(resptime) - Double.parseDouble(reqtime);
            conJson.getKeycore().set_timecost(timecost);
        }
        conJson.getResponse().setResptype(Q.getClassname(object));
        return conJson;
    }

    /**
     * 根据token查找存在map里的用户
     * @param token
     * @return
     */
    public UserJson getUserJson(String token) {
        return SysConst.SYS_SECUSER_TOKEN.get(token);
    }

    /**
     * FOBIDDEN返回中英提示
     * @param eng
     * @param chn
     * @return
     */
    public String getDescribe(String eng,String chn){
        if (Q.notNull(eng,chn))
            return eng + "/" + chn;
        else if (Q.notNull(eng) && !Q.notNull(chn))
            return eng;
        else
            return chn;
    }
}
