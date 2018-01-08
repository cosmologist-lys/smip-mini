package com.smip.controller.sys;

import com.smip.controller.BaseController;
import com.smip.entity.json.FeedbackJson;
import com.smip.entity.json.ReqHeadersMsg;
import com.smip.entity.sys.Secuser;
import com.smip.service.sys.SecuserService;
import com.smip.ulities.Q_Cipher;
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
@Api(value = "/users",description = "用户登陆",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/users")
public class TokenController extends BaseController<Secuser> {
    @Autowired
    private SecuserService secuserService;
    private String describe;

    @Override
    public ReqHeadersMsg beforeController(HttpServletRequest request) {
        return super.beforeController(request);
    }

    /* @ApiOperation(value="登陆并获取token", notes="",response = ResultJson.class)
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResultJson login(@RequestBody Secuser secuser, HttpServletRequest req){
        Assert.notNull(secuser,"secuser cannot be null");
        System.out.println("secuser="+secuser.toString());
        String describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        String password = (null != secuser && null != secuser.getPassWord())?
                Q_Cipher.md5(secuser.getPassWord()):"";
        secuser.setPassWord(password);
        Secuser user = secuserService.findOne(secuser.getId());
        System.out.println("user="+user.toString());

        if (user == null ){
            return new ResultJson(req);
        }
        return new ResultJson(true,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_BOOLEAN,describe);
    }*/
    @ApiOperation(value="登陆并获取token", notes="",response = FeedbackJson.class)
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public FeedbackJson findOne(@ModelAttribute("tokenModel") ReqHeadersMsg header){
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Secuser secuser = SysConst.SYS_SECUSER_LIST.stream()
                .filter((user)->user.getUserName().toLowerCase()
                        .equals(header.getUsername().toLowerCase()))
                .findFirst().get();
        return OK(describe,secuser,header);
    }
}
