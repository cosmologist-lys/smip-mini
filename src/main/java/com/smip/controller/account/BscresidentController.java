package com.smip.controller.account;


import com.smip.controller.BaseController;
import com.smip.entity.ResultJson;
import com.smip.entity.account.Bscresident;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Api(value = "/resident",description = "居民信息",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/resident")
public class BscresidentController extends BaseController<Bscresident>{
    @Override
    @ApiOperation(value="获取单个居民", notes="" ,response = ResultJson.class)
    @RequestMapping(value="/query/{id}", method= RequestMethod.GET)
    public ResultJson querySingleById(@PathVariable("id") Integer id, HttpServletRequest req) {
        return super.querySingleById(id, req);
    }

    @Override
    @ApiOperation(value="查询居民总条数", notes="",response = ResultJson.class)
    @RequestMapping(value="/count", method=RequestMethod.GET)
    public ResultJson countAll(HttpServletRequest req) {
        return super.countAll(req);
    }

    @Override
    @ApiOperation(value="根据条件查询居民个数", notes="",response = ResultJson.class)
    @RequestMapping(value="/count/one", method=RequestMethod.POST)
    public ResultJson countByObject(Bscresident bscresident, HttpServletRequest req) {
        return super.countByObject(bscresident, req);
    }

    @Override
    @ApiOperation(value="根据条件查询多个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/query/page={page}&size={size}", method=RequestMethod.POST)
    public ResultJson queryListByObject(Bscresident bscresident,@PathVariable("page") int page, @PathVariable("size") int size, HttpServletRequest req) {
        return super.queryListByObject(bscresident, page, size, req);
    }

    @Override
    @ApiOperation(value="根据id查找居民是否存在", notes="",response = ResultJson.class)
    @RequestMapping(value="/exist/{id}", method=RequestMethod.GET)
    public ResultJson checkExistById(@PathVariable("id") int id, HttpServletRequest req) {
        return super.checkExistById(id, req);
    }

    @Override
    @ApiOperation(value="根据条件查询居民是否存在", notes="",response = ResultJson.class)
    @RequestMapping(value="/exist/one", method=RequestMethod.POST)
    public ResultJson checkExistByObject(Bscresident bscresident, HttpServletRequest req) {
        System.out.println(bscresident.toString());
        return super.checkExistByObject(bscresident, req);
    }

    @Override
    @ApiOperation(value="保存单个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/save/one", method=RequestMethod.POST)
    public ResultJson saveObject(Bscresident bscresident, HttpServletRequest req) {
        return super.saveObject(bscresident, req);
    }

    @Override
    @ApiOperation(value="根据条件查询多个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/save/list", method=RequestMethod.POST)
    public ResultJson saveListObject(List<Bscresident> bscresidents, HttpServletRequest req) {
        return super.saveListObject(bscresidents, req);
    }

    @Override
    @ApiOperation(value="根据id删除单个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResultJson deleteObject(@PathVariable("id") int id, HttpServletRequest req) {
        return super.deleteObject(id, req);
    }

    @Override
    @ApiOperation(value="根据条件删除单个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/delete/one", method=RequestMethod.DELETE)
    public ResultJson deleteObject(Bscresident bscresident, HttpServletRequest req) {
        return super.deleteObject(bscresident, req);
    }

    @Override
    @ApiOperation(value="根据条件删除多个居民", notes="",response = ResultJson.class)
    @RequestMapping(value="/delete/list", method=RequestMethod.DELETE)
    public ResultJson deleteListByObject(Bscresident bscresident, HttpServletRequest req) {
        return super.deleteListByObject(bscresident, req);
    }
}