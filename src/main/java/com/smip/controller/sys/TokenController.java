package com.smip.controller.sys;

import com.smip.controller.BaseController;
import com.smip.entity.ReqHeadersMsg;
import com.smip.entity.sys.Secuser;
import com.smip.service.sys.SecuserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/users",description = "用户登陆",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/users")
public class TokenController extends BaseController<Secuser> {
    @Autowired
    private SecuserService secuserService;

    @Override
    public ReqHeadersMsg beforeController(HttpServletRequest request) {
        return super.beforeController(request);
    }

    /* @ApiOperation(value="登陆并获取token", notes="",response = ResultJson.class)
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResultJson login(@RequestBody Secuser secuser, HttpServletRequest req){
        Assert.notNull(secuser,"secuser cannot be null");
        System.out.println("secuser="+secuser.toString());
        String describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        String password = (null != secuser && null != secuser.getPassWord())?
                StringTools.md5(secuser.getPassWord()):"";
        secuser.setPassWord(password);
        Secuser user = secuserService.findOne(secuser.getId());
        System.out.println("user="+user.toString());

        if (user == null ){
            return new ResultJson(req);
        }
        return new ResultJson(true,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_BOOLEAN,describe);
    }*/
}
