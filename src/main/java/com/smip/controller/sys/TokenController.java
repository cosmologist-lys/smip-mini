package com.smip.controller.sys;

import com.smip.controller.BaseController;
import com.smip.entity.ResultJson;
import com.smip.entity.sys.Secuser;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.GlobalConstance;
import com.smip.ulities.StringTools;
import com.smip.ulities.token.TokenManager;
import com.smip.ulities.token.TokenModel;
import com.smip.ulities.webComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/user",description = "用户登陆",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/user")
public class TokenController extends BaseController<Secuser> {
    @Autowired
    private SecuserService secuserService;
    @Autowired
    private TokenManager tokenManager;


    @ApiOperation(value="登陆并获取token", notes="",response = ResultJson.class)
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResultJson login(Secuser secuser, HttpServletRequest req){
        Assert.notNull(secuser,"secuser cannot be null");
        System.out.println("secuser="+secuser.toString());
        String describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        Secuser user = secuserService.findOne(secuser);
        System.out.println("user="+user.toString());
        String password = (null != secuser)?
                StringTools.md5(secuser.getPassWord()):"";
        if (user == null || !user.getPassWord().equals(password)){
            return new ResultJson(req);
        }
        TokenModel token = tokenManager.createToken(user.getId());
        return new ResultJson(token,HttpStatus.OK, GlobalConstance.RESULTJSON_TYPE_OBJECT,describe);
    }
}
