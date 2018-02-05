package com.smip.controller.basement;

import com.smip.controller.BaseController;
import com.smip.entity.basement.Area;
import com.smip.entity.json.ConJson;
import com.smip.error.ErrorDescribe;
import com.smip.service.basement.AreaService;
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

@Api(value = "/area", description = "区域信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/area")
public class AreaController extends BaseController<Area> {
    @Autowired
    private AreaService areaService;
    private Area editArea;
    private String describe;

    @ApiOperation(value = "根据id获取区域信息", response = ConJson.class)
    @RequestMapping(value = "/query/id/{id}", method = RequestMethod.GET)
    public ConJson findById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!(id > 0)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, areaService.findOne(id), conJson);
    }

    @ApiOperation(value = "根据条件获取区域信息", response = ConJson.class)
    @RequestMapping(value = "/query/one", method = RequestMethod.POST)
    public ConJson findByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Area area) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(area)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        return OK(describe, areaService.findOne(area), conJson);
    }

    @ApiOperation(value = "获取区域信息总条数", response = ConJson.class)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ConJson count(@ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, areaService.count(), conJson);
    }

    @ApiOperation(value = "根据条件获取区域信息总条数", response = ConJson.class)
    @RequestMapping(value = "/count/one", method = RequestMethod.POST)
    public ConJson countOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Area area) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(area)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        return OK(describe, areaService.count(), conJson);
    }

    @ApiOperation(value = "翻页查询多个区域信息", response = ConJson.class)
    @RequestMapping(value = "/query/{page}/{size}", method = RequestMethod.GET)
    public ConJson findManyByPage(@PathVariable("page") int page, @PathVariable("size") int size, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, queryByPage(page, size), conJson);
    }

    @ApiOperation(value = "翻页查询多个区域信息", response = ConJson.class)
    @RequestMapping(value = "/query/sort", method = RequestMethod.GET)
    public ConJson findManyByPageParam(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", required = true, defaultValue = "12") Integer limit,
                                       @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, queryByPage(page, limit), conJson);
    }

    /**
     * 提取公用函数体
     *
     * @param page
     * @param limit
     * @return
     */
    public Page<Area> queryByPage(int page, int limit) {
        Pageable pageable = new PageRequest(page, limit, Sort.Direction.DESC, "id");
        return areaService.findListByObject(new Area(), pageable);
    }

    @ApiOperation(value = "根据id查找区域是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/{id}", method = RequestMethod.GET)
    public ConJson isExistById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!(id > 0)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, areaService.exist(id), conJson);
    }

    @ApiOperation(value = "根据条件查找区域是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/one", method = RequestMethod.POST)
    public ConJson isExistByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Area area) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(area)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        return OK(describe, areaService.exist(area), conJson);
    }

    @ApiOperation(value = "修改单个区域信息", response = ConJson.class)
    @RequestMapping(value = "/edit/one", method = RequestMethod.POST)
    public ConJson edit(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Area area) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(area)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        areaService.update(area);
        return OK(describe, true, conJson);
    }

    @ApiOperation(value = "新增单个区域信息", response = ConJson.class)
    @RequestMapping(value = "/save/one", method = RequestMethod.PUT)
    public ConJson save(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Area area) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(area)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        if (area.getId() > 0) return FORBIDDEN(ErrorDescribe.EXCLUDE_ID_SAVE.getDescribe());
        editArea = areaService.save(area);
        return Q.notNull(editArea) ?
                OK(describe, true, conJson) : OK(describe, false, conJson);
    }

    @ApiOperation(value = "根据id删除单个区域", response = ConJson.class)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ConJson deleteById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!(id > 0)) return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        areaService.deleteOne(id);
        return OK(describe, true, conJson);
    }

    @ApiOperation(value = "根据条件删除单个区域", response = ConJson.class)
    @RequestMapping(value = "/delete/one", method = RequestMethod.DELETE)
    public ConJson deleteByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Area area) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (!Q.notNull(area)) return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        if (area.getId() > 0) {
            areaService.deleteOne(area.getId());
        } else {
            areaService.deleteOne(area);
        }
        return OK(describe, true, conJson);
    }
}
