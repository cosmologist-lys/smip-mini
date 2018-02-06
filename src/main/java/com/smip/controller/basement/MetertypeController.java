package com.smip.controller.basement;

import com.smip.controller.BaseController;
import com.smip.entity.basement.Metertype;
import com.smip.entity.json.ConJson;
import com.smip.error.ErrorDescribe;
import com.smip.service.basement.MetertypeService;
import com.smip.ulities.Q;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/metertype", description = "表具型号信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/metertype")
public class MetertypeController extends BaseController<Metertype> {
    @Autowired
    private MetertypeService metertypeService;
    private Metertype editMetertype;
    private String describe;

    @ApiOperation(value = "根据id获取表具信息", response = ConJson.class)
    @RequestMapping(value = "/query/id/{id}", method = RequestMethod.GET)
    public ConJson queryById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        return super.findById(conJson, id);
    }

    @ApiOperation(value = "根据条件获取表具信息", response = ConJson.class)
    @RequestMapping(value = "/query/one", method = RequestMethod.POST)
    public ConJson findByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Metertype metertype) {
        return super.findByCondition(conJson, metertype);
    }

    @ApiOperation(value = "获取表具信息总条数", response = ConJson.class)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ConJson count(@ModelAttribute("tokenModel") ConJson conJson) {
        return super.countAll(conJson);
    }

    @ApiOperation(value = "根据条件获取表具信息总条数", response = ConJson.class)
    @RequestMapping(value = "/count/one", method = RequestMethod.POST)
    public ConJson countOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Metertype metertype) {
        return super.countByCondition(conJson, metertype);
    }

    @ApiOperation(value = "翻页查询多个表具信息", response = ConJson.class)
    @RequestMapping(value = "/query/{page}/{size}", method = RequestMethod.GET)
    public ConJson findManyByPage(@PathVariable("page") int page, @PathVariable("size") int size, @ModelAttribute("tokenModel") ConJson conJson) {
        return super.findManyByUri(page, size, conJson, new Metertype());
    }

    @ApiOperation(value = "翻页查询多个表具信息", response = ConJson.class)
    @RequestMapping(value = "/query/sort", method = RequestMethod.GET)
    public ConJson findManyByPageParam(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", required = true, defaultValue = "12") Integer limit,
                                       @ModelAttribute("tokenModel") ConJson conJson) {
        return super.findManyByParam(page, limit, conJson, new Metertype());
    }


    @ApiOperation(value = "根据id查找区域是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/id/{id}", method = RequestMethod.GET)
    public ConJson isExistById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        return super.isExistById(conJson, id);
    }

    @ApiOperation(value = "根据条件查找区域是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/one", method = RequestMethod.POST)
    public ConJson isExistByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Metertype metertype) {
        return super.isExistByCondition(conJson, metertype);
    }

    @ApiOperation(value = "修改单个表具信息", response = ConJson.class)
    @RequestMapping(value = "/edit/one", method = RequestMethod.POST)
    public ConJson edit(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Metertype metertype) {
        return super.edit(conJson, metertype, metertype.getId());
    }

    @ApiOperation(value = "批量修改表具信息", response = ConJson.class)
    @RequestMapping(value = "/edit/many", method = RequestMethod.POST)
    public ConJson editMany(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody List<Metertype> metertypeList) {
        for (Metertype comm : metertypeList) {
            if (!Q.notNull(comm.getId())) { //update必须带有id
                return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
            }
        }
        return super.editMany(conJson, metertypeList);
    }

    @ApiOperation(value = "新增单个表具信息", response = ConJson.class)
    @RequestMapping(value = "/save/one", method = RequestMethod.PUT)
    public ConJson save(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Metertype metertype) {
        return super.save(conJson, metertype, metertype.getId());
    }

    @ApiOperation(value = "批量新增表具信息", response = ConJson.class)
    @RequestMapping(value = "/save/many", method = RequestMethod.PUT)
    public ConJson saveMany(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody List<Metertype> metertypeList) {
        for (Metertype comm : metertypeList) {
            if (Q.notNull(comm.getId())) {  //save不能带有id
                return FORBIDDEN(ErrorDescribe.EXCLUDE_ID_SAVE.getDescribe());
            }
        }
        return super.saveMany(conJson, metertypeList);
    }

    @ApiOperation(value = "根据id删除单个表具", response = ConJson.class)
    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.DELETE)
    public ConJson deleteById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        return super.deleteById(conJson, id);
    }

    @ApiOperation(value = "根据条件删除单个表具", response = ConJson.class)
    @RequestMapping(value = "/delete/one", method = RequestMethod.DELETE)
    public ConJson deleteByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Metertype metertype) {
        return super.deleteByOne(conJson, metertype, metertype.getId());
    }

    @ApiOperation(value = "根据条件批量删除表具", response = ConJson.class)
    @RequestMapping(value = "/delete/many", method = RequestMethod.DELETE)
    public ConJson deleteMany(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody List<Metertype> metertypeList) {
        for (Metertype comm : metertypeList) {
            if (!Q.notNull(comm) || !Q.notNull(comm.getId())) {  //delete必须带有id
                return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
            }
        }
        return super.deleteMany(conJson, metertypeList);
    }


}
