package com.smip.controller;

import com.smip.entity.json.*;
import com.smip.error.ErrorDescribe;
import com.smip.service.BaseService;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cpnt;
import com.smip.ulities.SysConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * controller父类。公共函数beforeController验证合法性
 * 定义OK,FORBIDDEN,NOTFOUND用来返回对应情况下的Feedbackjson对象
 */
public class BaseController<T> {
    @Autowired
    private SecuserService secuserService;
    @Autowired
    private BaseService<T> baseService;

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
        if (!Q.notNull(token)) {
            return login(request);
        } else {
            return conns(request, token);
        }
    }

    /**
     * headers={username,psw,auth,unitcode}，第一次请求 = 登陆
     *
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
     *
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
     *
     * @return
     */
    public ConJson FORBIDDEN() {
        respmodule = new Respmodule();
        respmodule.setHttpStatus(HttpStatus.FORBIDDEN)
                .setDescribe(ErrorDescribe.LIMITED_PERMISSION.getDescribe());
        return new ConJson(keycore, reqmodule, respmodule);
    }

    public ConJson FORBIDDEN(String tag) {
        respmodule = new Respmodule();
        respmodule.setHttpStatus(HttpStatus.FORBIDDEN)
                .setDescribe(tag);
        return new ConJson(keycore, reqmodule, respmodule);
    }

    /**
     * 请求了一个不存在的url时返回404
     * todo
     *
     * @return
     */
    public ConJson NOTFOUND() {
        respmodule = new Respmodule();
        respmodule.setHttpStatus(HttpStatus.NOT_FOUND)
                .setDescribe(ErrorDescribe.NOT_FOUND.getDescribe());
        return new ConJson(keycore, reqmodule, respmodule);
    }

    /**
     * 抽离共用部分。打包conjson
     *
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
        String reqtime = conJson.getRequest().getReqtime();
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
     *
     * @param token
     * @return
     */
    public UserJson getUserJson(String token) {
        return SysConst.SYS_SECUSER_TOKEN.get(token);
    }


    /**
     * update数据，必须带有id
     * 父类做基本流程
     *
     * @param conJson
     * @param t
     * @return
     */
    public ConJson<T> edit(ConJson conJson, T t, Integer id) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(t)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        if (!Q.notNull(id)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        baseService.update(t);
        return OK(describe, true, conJson);
    }

    /**
     * 批量update,每个对象必须带id,在子类判断
     *
     * @param conJson
     * @param ts
     * @return
     */
    public ConJson<T> editMany(ConJson conJson, List<T> ts) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(ts)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        baseService.update(ts);
        return OK(describe, true, conJson);
    }

    /**
     * save数据，必须不带id
     * 父类做基本流程
     *
     * @param conJson
     * @param t
     * @return
     */
    public ConJson save(ConJson conJson, T t, Integer id) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(t)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        if (Q.notNull(id)) return FORBIDDEN(ErrorDescribe.EXCLUDE_ID_SAVE.getDescribe());
        Object editT = baseService.save(t);
        return Q.notNull(editT) ?
                OK(describe, true, conJson) : OK(describe, false, conJson);
    }

    /**
     * 批量save数据，必须不带id,交给子类判断
     *
     * @param conJson
     * @param ts
     * @return
     */
    public ConJson saveMany(ConJson conJson, List<T> ts) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(ts)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        List<T> saveT = baseService.save(ts);
        return Q.notNull(saveT) && saveT.size() == ts.size() ?
                OK(describe, true, conJson) : OK(describe, false, conJson);
    }

    /**
     * 根据id删除
     *
     * @param conJson
     * @param id
     * @return
     */
    public ConJson deleteById(ConJson conJson, Integer id) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(id) && id > 0) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        baseService.deleteOne(id);
        return OK(describe, true, conJson);
    }

    /**
     * 根据对象删除。必须带id
     *
     * @param conJson
     * @param t
     * @param id
     * @return
     */
    public ConJson deleteByOne(ConJson conJson, T t, Integer id) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(t)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        if (Q.notNull(id) && id > 0) {
            baseService.deleteOne(id);
        } else {
            return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        }
        return OK(describe, true, conJson);
    }

    /**
     * 批量删除，必须带有id，交给子类判断
     *
     * @param conJson
     * @param ts
     * @return
     */
    public ConJson deleteMany(ConJson conJson, List<T> ts) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(ts)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        baseService.deleteList(ts);
        return OK(describe, true, conJson);
    }

    /**
     * 根据id查找
     *
     * @param conJson
     * @param id
     * @return
     */
    public ConJson findById(ConJson conJson, Integer id) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(id) || !(id > 0)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, baseService.findOne(id), conJson);
    }

    /**
     * 根据条件查找
     *
     * @param conJson
     * @param t
     * @return
     */
    public ConJson findByCondition(ConJson conJson, T t) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(t)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        return OK(describe, baseService.findOne(t), conJson);
    }

    /**
     * 获取该表总条数
     *
     * @param conJson
     * @return
     */
    public ConJson countAll(ConJson conJson) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, baseService.count(), conJson);
    }

    /**
     * 根据条件查询总条数
     *
     * @param conJson
     * @param t
     * @return
     */
    public ConJson countByCondition(ConJson conJson, T t) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(t)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        return OK(describe, baseService.count(), conJson);
    }

    /**
     * 翻页查询，方式1,uri方式传递
     *
     * @param page
     * @param size
     * @param conJson
     * @return
     */
    public ConJson findManyByUri(Integer page, Integer size, ConJson conJson, T t) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(page, size)) return FORBIDDEN(ErrorDescribe.EMPTY_PARAMS_OPTION.getDescribe());
        return OK(describe, baseService.findListByObject(t, new PageRequest(page, size, Sort.Direction.DESC, "id")), conJson);
    }

    /**
     * 翻页查询，方式2，params方式传递
     *
     * @param page
     * @param limit
     * @param conJson
     * @return
     */
    public ConJson findManyByParam(Integer page, Integer limit, ConJson conJson, T t) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, baseService.findListByObject(t, new PageRequest(page, limit, Sort.Direction.DESC, "id")), conJson);
    }

    /**
     * 根据id查找是否存在
     *
     * @param conJson
     * @param id
     * @return
     */
    public ConJson isExistById(ConJson conJson, Integer id) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(id) || !(id > 0)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, baseService.exist(id), conJson);
    }

    /**
     * 根据条件查找是否存在
     *
     * @param conJson
     * @param t
     * @return
     */
    public ConJson isExistByCondition(ConJson conJson, T t) {
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(t)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, baseService.exist(t), conJson);
    }
}
