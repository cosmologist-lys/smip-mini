package com.smip.controller.basement;

import com.smip.controller.BaseController;
import com.smip.entity.basement.Community;
import com.smip.entity.json.ConJson;
import com.smip.error.ErrorDescribe;
import com.smip.service.basement.CommunityService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cpnt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(value = "/community", description = "小区信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/community")
public class CommunityController extends BaseController<Community> {
    @Autowired
    private CommunityService communityService;
    private Community editCommunity;
    private String describe;

    @ApiOperation(value = "根据id获取小区信息", response = ConJson.class)
    @RequestMapping(value = "/query/id/{id}", method = RequestMethod.GET)
    public ConJson queryById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id){
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!(id > 0)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, communityService.findOne(id), conJson);
    }

    @ApiOperation(value = "根据条件获取小区信息", response = ConJson.class)
    @RequestMapping(value = "/query/one", method = RequestMethod.POST)
    public ConJson findByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Community community){
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(community)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        return OK(describe, communityService.findOne(community), conJson);
    }

    @ApiOperation(value = "获取小区信息总条数", response = ConJson.class)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ConJson count(@ModelAttribute("tokenModel") ConJson conJson){
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe,communityService.count(),conJson);
    }

    @ApiOperation(value = "根据条件获取小区信息总条数", response = ConJson.class)
    @RequestMapping(value = "/count/one", method = RequestMethod.POST)
    public ConJson countOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Community community){
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(community)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        return OK(describe, communityService.count(), conJson);
    }

    @ApiOperation(value = "翻页查询多个小区信息", response = ConJson.class)
    @RequestMapping(value = "/query/{page}/{size}", method = RequestMethod.GET)
    public ConJson findManyByPage(@PathVariable("page") int page, @PathVariable("size") int size, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, queryByPage(page, size), conJson);
    }

    @ApiOperation(value = "翻页查询多个小区信息", response = ConJson.class)
    @RequestMapping(value = "/query/sort", method = RequestMethod.GET)
    public ConJson findManyByPageParam(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", required = true, defaultValue = "12") Integer limit,
                                       @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, queryByPage(page, limit), conJson);
    }

    public Page<Community> queryByPage(int page, int limit) {
        Pageable pageable = new PageRequest(page, limit, Sort.Direction.DESC, "id");
        return communityService.findListByObject(new Community(), pageable);
    }

    @ApiOperation(value = "根据id查找区域是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/{id}", method = RequestMethod.GET)
    public ConJson isExistById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!(id > 0)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, communityService.exist(id), conJson);
    }

    @ApiOperation(value = "根据条件查找区域是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/one", method = RequestMethod.POST)
    public ConJson isExistByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Community community) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(community)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, communityService.exist(community), conJson);
    }

    @ApiOperation(value = "修改单个小区信息", response = ConJson.class)
    @RequestMapping(value = "/edit/one", method = RequestMethod.POST)
    public ConJson edit(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Community community) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(community)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        communityService.update(community);
        return OK(describe, true, conJson);
    }

    @ApiOperation(value = "新增单个小区信息", response = ConJson.class)
    @RequestMapping(value = "/save/one", method = RequestMethod.PUT)
    public ConJson save(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Community community) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(community)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        if (community.getId() > 0) return FORBIDDEN(ErrorDescribe.EXCLUDE_ID_SAVE.getDescribe());
        editCommunity = communityService.save(community);
        return Q.notNull(editCommunity) ?
                OK(describe, true, conJson) : OK(describe, false, conJson);
    }
    @ApiOperation(value = "根据id删除单个小区", response = ConJson.class)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ConJson deleteById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!(id > 0)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        communityService.deleteOne(id);
        return OK(describe, true, conJson);
    }

    @ApiOperation(value = "根据条件删除单个小区", response = ConJson.class)
    @RequestMapping(value = "/delete/one", method = RequestMethod.DELETE)
    public ConJson deleteByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Community community) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(community)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        if (community.getId() > 0) {
            communityService.deleteOne(community.getId());
        } else {
            communityService.deleteOne(community);
        }
        return OK(describe, true, conJson);
    }

}
