package com.smip.controller.sys;

import com.smip.controller.BaseController;
import com.smip.entity.json.ConJson;
import com.smip.entity.sys.Secuser;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.Q_Cpnt;
import com.smip.ulities.SysConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * tokenController = secuserController
 */
@Api(value = "/users", description = "用户登陆", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/users")
public class TokenController extends BaseController<Secuser> {
    @Autowired
    private SecuserService secuserService;
    private String describe;

    @Override
    public ConJson beforeController(HttpServletRequest request) {
        return super.beforeController(request);
    }

    @ApiOperation(value = "登陆并获取token", notes = "", response = ConJson.class)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ConJson findOne(@ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        Secuser secuser = SysConst.SYS_SECUSER_LIST.stream()
                .filter((user) -> user.getUserName().toLowerCase()
                        .equals(conJson.getKeycore().get_tkn().toLowerCase()))
                        .findFirst().get();
        secuser.setPassWord("");//返回的JSON清空密码防止入侵
        return OK(describe,secuser,conJson);
    }
    @ApiOperation(value = "登出并清空token", notes = "", response = ConJson.class)
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ConJson logout(@ModelAttribute("tokenModel") ConJson conJson){
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        String token = conJson.getKeycore().get_token();
        secuserService.clearToken(token);
        return OK(describe,true,conJson);
    }

}
