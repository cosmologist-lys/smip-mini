package com.smip.controller.account;


import com.smip.controller.BaseController;
import com.smip.entity.FeedbackJson;
import com.smip.entity.ReqHeadersMsg;
import com.smip.entity.ReqInfoMsg;
import com.smip.entity.account.Bscresident;
import com.smip.service.account.BscresidentService;
import com.smip.ulities.GlobalConstance;
import com.smip.ulities.webComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(value = "/resident",description = "居民信息",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/resident")
public class BscresidentController extends BaseController<Bscresident>{
    @Autowired
    private BscresidentService bscresidentService;
    private FeedbackJson<Bscresident> json;
    private String describe;


    @Override
    public ReqHeadersMsg beforeController(HttpServletRequest request) {
        return super.beforeController(request);
    }

    @ApiOperation(value="获取单个居民", notes="" ,response = FeedbackJson.class)
    @RequestMapping(value="/query/{id}", method= RequestMethod.GET)
    public FeedbackJson<Bscresident> querySingleById(@PathVariable("id") Integer id, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident person = bscresidentService.findOne(id);
        if (null != person)
            return OK(describe,person,header,GlobalConstance.RESULTJSON_TYPE_OBJECT,1);
        else
            return NOTFOUND(describe,header,GlobalConstance.RESULTJSON_TYPE_OBJECT);
    }

   @ApiOperation(value="查询居民总条数", notes="",response = FeedbackJson.class)
    @RequestMapping(value="/count", method=RequestMethod.GET)
    public FeedbackJson countAll(@ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        int total = bscresidentService.count();
        if (total > 0)
            return OK(describe,null,header,GlobalConstance.RESULTJSON_TYPE_INTEGER,total);
        else
            return NOTFOUND(describe,header,GlobalConstance.RESULTJSON_TYPE_INTEGER);
    }
/*
    @ApiOperation(value="根据条件查询居民个数", notes="",response = ResultJson.class)
    @RequestMapping(value="/count/one", method=RequestMethod.POST)
    public ResultJson countByObject(Bscresident bscresident, HttpServletRequest req) {
        return super.countByObject(bscresident, req);
    }

    @ApiOperation(value="根据条件查询多个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/query/page={page}&size={size}", method=RequestMethod.POST)
    public ResultJson queryListByObject(Bscresident bscresident,@PathVariable("page") int page, @PathVariable("size") int size, HttpServletRequest req) {
        return super.queryListByObject(bscresident, page, size, req);
    }

    @ApiOperation(value="根据id查找居民是否存在", notes="",response = ResultJson.class)
    @RequestMapping(value="/exist/{id}", method=RequestMethod.GET)
    public ResultJson checkExistById(@PathVariable("id") int id, HttpServletRequest req) {
        return super.checkExistById(id, req);
    }

    @ApiOperation(value="根据条件查询居民是否存在", notes="",response = ResultJson.class)
    @RequestMapping(value="/exist/one", method=RequestMethod.POST)
    public ResultJson checkExistByObject(Bscresident bscresident, HttpServletRequest req) {
        System.out.println(bscresident.toString());
        return super.checkExistByObject(bscresident, req);
    }

    @ApiOperation(value="保存单个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/save/one", method=RequestMethod.POST)
    public ResultJson saveObject(Bscresident bscresident, HttpServletRequest req) {
        return super.saveObject(bscresident, req);
    }

    @ApiOperation(value="根据条件查询多个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/save/list", method=RequestMethod.POST)
    public ResultJson saveListObject(List<Bscresident> bscresidents, HttpServletRequest req) {
        return super.saveListObject(bscresidents, req);
    }

    @ApiOperation(value="根据id删除单个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResultJson deleteObject(@PathVariable("id") int id, HttpServletRequest req) {
        return super.deleteObject(id, req);
    }

    @ApiOperation(value="根据条件删除单个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/delete/one", method=RequestMethod.DELETE)
    public ResultJson deleteObject(Bscresident bscresident, HttpServletRequest req) {
        return super.deleteObject(bscresident, req);
    }

    @ApiOperation(value="根据条件删除多个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/delete/list", method=RequestMethod.DELETE)
    public ResultJson deleteListByObject(Bscresident bscresident, HttpServletRequest req) {
        return super.deleteListByObject(bscresident, req);
    }*/
}