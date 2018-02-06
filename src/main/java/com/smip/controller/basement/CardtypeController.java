package com.smip.controller.basement;

import com.smip.controller.BaseController;
import com.smip.entity.basement.Cardtype;
import com.smip.entity.json.ConJson;
import com.smip.error.ErrorDescribe;
import com.smip.service.basement.CardtypeService;
import com.smip.ulities.Q;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/cardtype", description = "卡类型信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/cardtype")
public class CardtypeController extends BaseController<Cardtype>{
    @Autowired
    private CardtypeService cardtypeService;
    private Cardtype editCardtype;
    private String describe;

    @ApiOperation(value = "根据id获取卡类型信息", response = ConJson.class)
    @RequestMapping(value = "/query/id/{id}", method = RequestMethod.GET)
    public ConJson queryById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        return super.findById(conJson, id);
    }

    @ApiOperation(value = "根据条件获取卡类型信息", response = ConJson.class)
    @RequestMapping(value = "/query/one", method = RequestMethod.POST)
    public ConJson findByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Cardtype cardtype) {
        return super.findByCondition(conJson, cardtype);
    }

    @ApiOperation(value = "获取卡类型信息总条数", response = ConJson.class)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ConJson count(@ModelAttribute("tokenModel") ConJson conJson) {
        return super.countAll(conJson);
    }

    @ApiOperation(value = "根据条件获取卡类型信息总条数", response = ConJson.class)
    @RequestMapping(value = "/count/one", method = RequestMethod.POST)
    public ConJson countOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Cardtype cardtype) {
        return super.countByCondition(conJson, cardtype);
    }

    @ApiOperation(value = "翻页查询多个卡类型信息", response = ConJson.class)
    @RequestMapping(value = "/query/{page}/{size}", method = RequestMethod.GET)
    public ConJson findManyByPage(@PathVariable("page") int page, @PathVariable("size") int size, @ModelAttribute("tokenModel") ConJson conJson) {
        return super.findManyByUri(page, size, conJson, new Cardtype());
    }

    @ApiOperation(value = "翻页查询多个卡类型信息", response = ConJson.class)
    @RequestMapping(value = "/query/sort", method = RequestMethod.GET)
    public ConJson findManyByPageParam(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", required = true, defaultValue = "12") Integer limit,
                                       @ModelAttribute("tokenModel") ConJson conJson) {
        return super.findManyByParam(page, limit, conJson, new Cardtype());
    }


    @ApiOperation(value = "根据id查找区域是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/id/{id}", method = RequestMethod.GET)
    public ConJson isExistById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        return super.isExistById(conJson, id);
    }

    @ApiOperation(value = "根据条件查找区域是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/one", method = RequestMethod.POST)
    public ConJson isExistByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Cardtype cardtype) {
        return super.isExistByCondition(conJson, cardtype);
    }

    @ApiOperation(value = "修改单个卡类型信息", response = ConJson.class)
    @RequestMapping(value = "/edit/one", method = RequestMethod.POST)
    public ConJson edit(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Cardtype cardtype) {
        return super.edit(conJson, cardtype, cardtype.getId());
    }

    @ApiOperation(value = "批量修改卡类型信息", response = ConJson.class)
    @RequestMapping(value = "/edit/many", method = RequestMethod.POST)
    public ConJson editMany(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody List<Cardtype> cardtypeList) {
        for (Cardtype comm : cardtypeList) {
            if (!Q.notNull(comm.getId())) { //update必须带有id
                return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
            }
        }
        return super.editMany(conJson, cardtypeList);
    }

    @ApiOperation(value = "新增单个卡类型信息", response = ConJson.class)
    @RequestMapping(value = "/save/one", method = RequestMethod.PUT)
    public ConJson save(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Cardtype cardtype) {
        return super.save(conJson, cardtype, cardtype.getId());
    }

    @ApiOperation(value = "批量新增卡类型信息", response = ConJson.class)
    @RequestMapping(value = "/save/many", method = RequestMethod.PUT)
    public ConJson saveMany(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody List<Cardtype> cardtypeList) {
        for (Cardtype comm : cardtypeList) {
            if (Q.notNull(comm.getId())) {  //save不能带有id
                return FORBIDDEN(ErrorDescribe.EXCLUDE_ID_SAVE.getDescribe());
            }
        }
        return super.saveMany(conJson, cardtypeList);
    }

    @ApiOperation(value = "根据id删除单个卡类型", response = ConJson.class)
    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.DELETE)
    public ConJson deleteById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        return super.deleteById(conJson, id);
    }

    @ApiOperation(value = "根据条件删除单个卡类型", response = ConJson.class)
    @RequestMapping(value = "/delete/one", method = RequestMethod.DELETE)
    public ConJson deleteByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Cardtype cardtype) {
        return super.deleteByOne(conJson, cardtype, cardtype.getId());
    }

    @ApiOperation(value = "根据条件批量删除卡类型", response = ConJson.class)
    @RequestMapping(value = "/delete/many", method = RequestMethod.DELETE)
    public ConJson deleteMany(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody List<Cardtype> cardtypeList) {
        for (Cardtype comm : cardtypeList) {
            if (!Q.notNull(comm) || !Q.notNull(comm.getId())) {  //delete必须带有id
                return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
            }
        }
        return super.deleteMany(conJson, cardtypeList);
    }

}
