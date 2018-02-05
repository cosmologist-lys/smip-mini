package com.smip.controller.basement;

import com.smip.controller.BaseController;
import com.smip.entity.basement.Encodesets;
import com.smip.entity.json.ConJson;
import com.smip.error.ErrorDescribe;
import com.smip.service.basement.EncodesetsService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cpnt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api(value = "/encode", description = "公司信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/encode")
public class EncodesetsController extends BaseController<Encodesets> {
    @Autowired
    private EncodesetsService encodesetsService;
    private Encodesets editencodesets;
    private String describe;

    @ApiOperation(value = "获取所有规则信息", response = ConJson.class)
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ConJson find(@ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, encodesetsService.findAllByPage(0, 10), conJson);
    }

    @ApiOperation(value = "修改单个规则信息", response = ConJson.class)
    @RequestMapping(value = "/edit/one", method = RequestMethod.POST)
    public ConJson edit(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Encodesets encode) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (null == encode || !Q.notNull(encode)) {
            return FORBIDDEN(ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe());
        } else {
            encodesetsService.update(encode);
            return OK(describe, true, conJson);
        }
    }

    @ApiOperation(value = "新增单个规则信息", response = ConJson.class)
    @RequestMapping(value = "/save/one", method = RequestMethod.PUT)
    public ConJson save(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Encodesets encode) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (null == encode || !Q.notNull(encode)) {
            describe = ErrorDescribe.EMPTY_OBJECT_OPTION.getDescribe();
        } else {
            if (encode.getId() > 0) {
                describe = ErrorDescribe.EXCLUDE_ID_SAVE.getDescribe();
            } else if (encodesetsService.count() >= 3) {
                describe = ErrorDescribe.TABLE_LIMITS.getDescribe();
            } else {
                editencodesets = encodesetsService.save(encode);
                return null == editencodesets ?
                        OK(describe, false, conJson) : OK(describe, true, conJson);
            }
        }
        return FORBIDDEN(describe);
    }

    @ApiOperation(value = "删除单个规则信息", response = ConJson.class)
    @RequestMapping(value = "/delete/one", method = RequestMethod.DELETE)
    public ConJson deleteByOne(@ModelAttribute("tokenModel") ConJson conJson, @RequestBody Encodesets encode) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (null == encode || !Q.notNull(encode)) {
            describe = ErrorDescribe.EMPTY_TABLE_OPTION.getDescribe();
        } else {
            editencodesets = encodesetsService.findOne(encode);
            if (Q.notNull(editencodesets)) {
                encodesetsService.deleteOne(editencodesets.getId());
                return OK(describe, true, conJson);
            }
        }
        return FORBIDDEN(describe);
    }

    @ApiOperation(value = "删除单个规则信息", response = ConJson.class)
    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.DELETE)
    public ConJson deleteById(@ModelAttribute("tokenModel") ConJson conJson, @PathVariable("id") int id) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (id > 0) {
            if (null != encodesetsService.findOne(id)) {
                encodesetsService.deleteOne(id);
                return OK(describe, true, conJson);
            }
        } else {
            describe = ErrorDescribe.EMPTY_ID_OPTION.getDescribe();
        }
        return FORBIDDEN(describe);
    }

}
