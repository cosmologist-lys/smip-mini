package com.smip.controller.basement;

import com.smip.controller.BaseController;
import com.smip.entity.basement.Company;
import com.smip.entity.json.ConJson;
import com.smip.error.ErrorDescribe;
import com.smip.service.basement.CompanyService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cpnt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/company", description = "公司信息,该项目只存在一个，所以uri不包含one修饰", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/company")
public class CompanyController extends BaseController<Company> {
    @Autowired
    private CompanyService companyService;
    private String describe;
    private Company editcompany;

    @Override
    public ConJson beforeController(HttpServletRequest request) {
        return super.beforeController(request);
    }

    @ApiOperation(value = "获取公司信息", response = ConJson.class)
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ConJson find(@ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, companyService.find(), conJson);
    }

    @ApiOperation(value = "修改公司信息", response = ConJson.class)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ConJson edit(@RequestBody Company company, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (Q.notNull(company,company.getId())){//存在id是更新
            companyService.update(company);
            return OK(describe, true, conJson);
        }else{
            return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        }
    }

    @ApiOperation(value = "修改公司信息", response = ConJson.class)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ConJson save(@RequestBody Company company, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (Q.notNull(company) && !Q.notNull(company.getId())) {//不存在id是保存
            if (Q.notNull(companyService.find())){
                return FORBIDDEN(ErrorDescribe.ONE_ROW_ONLY_SAVE.getDescribe());
            }
            company = companyService.save(company);
            return (Q.notNull(company)) ?
                    OK(describe, true, conJson) : OK(describe, false, conJson);
        } else {
            return FORBIDDEN(ErrorDescribe.EXCLUDE_ID_SAVE.getDescribe());
        }
    }

    @ApiOperation(value = "删除公司信息(默认删除，不需要传递id等信息)", response = ConJson.class)
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ConJson deleteByOne( @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (null != companyService.findAll()){
            editcompany = companyService.findAll().get(0);
            companyService.deleteOne(editcompany.getId());
            return OK(describe,true,conJson);
        }else{
            return FORBIDDEN(ErrorDescribe.EMPTY_TABLE_OPTION.getDescribe());
        }
    }

    @ApiOperation(value = "删除公司信息(传递id)", response = ConJson.class)
    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.GET)
    public ConJson deleteByOne( @ModelAttribute("tokenModel") ConJson conJson , @PathVariable("id") int id) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (id > 0){
            companyService.deleteOne(id);
            return OK(describe,true,conJson);
        }else{
            return FORBIDDEN(ErrorDescribe.EMPTY_ID_OPTION.getDescribe());
        }
    }

}
