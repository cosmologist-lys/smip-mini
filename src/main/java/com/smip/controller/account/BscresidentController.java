package com.smip.controller.account;


import com.smip.controller.BaseController;
import com.smip.entity.json.FeedbackJson;
import com.smip.entity.json.ReqHeadersMsg;
import com.smip.entity.account.Bscresident;
import com.smip.service.account.BscresidentService;
import com.smip.ulities.GlobalConstance;
import com.smip.ulities.webComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/resident",description = "居民信息",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/resident")
public class BscresidentController extends BaseController<Bscresident>{
    @Autowired
    private BscresidentService bscresidentService;
    private String describe;


    @Override
    public ReqHeadersMsg beforeController(HttpServletRequest request) {
        return super.beforeController(request);
    }

    @ApiOperation(value="获取单个居民", notes="" ,response = FeedbackJson.class)
    @GetMapping(value="/query/id/{id}")
    public FeedbackJson querySingleById(@PathVariable("id") int id, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident person = bscresidentService.findOne(id);
        if (null != person)
            return OK(describe,person,header,GlobalConstance.JSON_TYPE_OBJECT);
        else
            return NOTFOUND(describe,header,GlobalConstance.JSON_TYPE_OBJECT);
    }

    @ApiOperation(value="根据条件获取单个居民", notes="" ,response = FeedbackJson.class)
    @RequestMapping(value="/query/one", method= RequestMethod.POST)
    public FeedbackJson querySingleById(@RequestBody Bscresident person, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident p = bscresidentService.findOne(person);
        if (null != p)
            return OK(describe,p,header,GlobalConstance.JSON_TYPE_OBJECT);
        else
            return NOTFOUND(describe,header,GlobalConstance.JSON_TYPE_OBJECT);
    }

    @ApiOperation(value="根据name查询居民(模糊匹配)", notes="",response = FeedbackJson.class)
    @RequestMapping(value="/query/name/{name}", method=RequestMethod.GET)
    public FeedbackJson queryByNameLike(@PathVariable("name") String name,@ModelAttribute("tokenModel") ReqHeadersMsg header){
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident person = bscresidentService.findByNameLike(name);
        return OK(describe,person,header,GlobalConstance.JSON_TYPE_OBJECT);
    }

   @ApiOperation(value="查询居民总条数", notes="",response = FeedbackJson.class)
    @RequestMapping(value="/count", method=RequestMethod.GET)
    public FeedbackJson countAll(@ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        int total = bscresidentService.count();
        if (total > 0)
            return OK(describe,header,GlobalConstance.JSON_TYPE_INTEGER,total);
        else
            return NOTFOUND(describe,header,GlobalConstance.JSON_TYPE_INTEGER);
    }

    @ApiOperation(value="根据条件查询居民个数", notes="",response = FeedbackJson.class)
    @RequestMapping(value="/count/one", method=RequestMethod.POST)
    public FeedbackJson countByObject(@ModelAttribute("tokenModel") ReqHeadersMsg header,@RequestBody Bscresident bscresident) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        int total = bscresidentService.count(bscresident);
        return OK(describe,header,GlobalConstance.JSON_TYPE_INTEGER,total);
    }

    @ApiOperation(value="根据PAGE查询多个居民", notes="",response = FeedbackJson.class)
    @RequestMapping(value="/query/{page}/{size}", method=RequestMethod.GET)
    public FeedbackJson queryListByObject(@PathVariable("page") int page, @PathVariable("size") int size,@ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Pageable pageable = new PageRequest(page,size, Sort.Direction.DESC,"id");
        Page<Bscresident> persons = bscresidentService.findListByObject(new Bscresident(),pageable);
        return OK(describe,persons,header,GlobalConstance.JSON_TYPE_LIST_OBJECT,persons.getSize());
    }

    @ApiOperation(value="根据id查找居民是否存在", notes="",response = FeedbackJson.class)
    @RequestMapping(value="/exist/{id}", method=RequestMethod.GET)
    public FeedbackJson checkExistById(@PathVariable("id") int id,@ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        boolean flg = bscresidentService.exist(id);
        return OK(describe,header,GlobalConstance.JSON_TYPE_BOOLEAN,flg);
    }

    @ApiOperation(value="根据条件查询居民是否存在", notes="",response = FeedbackJson.class)
    @RequestMapping(value="/exist/one", method=RequestMethod.POST)
    public FeedbackJson checkExistByObject(@RequestBody Bscresident bscresident, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = webComponent.getMethodDiscribe(webComponent.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        boolean flg = bscresidentService.exist(bscresident);
        return OK(describe,header,GlobalConstance.JSON_TYPE_BOOLEAN,flg);
    }
/*
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