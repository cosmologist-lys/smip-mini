package com.smip.controller.basement;

import com.smip.controller.BaseController;
import com.smip.entity.basement.Company;
import com.smip.entity.json.ConJson;
import com.smip.service.basement.CompanyService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cpnt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/company", description = "公司信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/company")
public class CompanyController extends BaseController<Company> {
    @Autowired
    private CompanyService companyService;
    private String describe;
    private Company company = new Company();

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
            String warn_eng = "update object must apply the key:id";
            String warn_chn = "更新对象必须传入主键id";
            return FORBIDDEN(getDescribe(warn_eng, warn_chn));
        }
    }

    @ApiOperation(value = "修改公司信息", response = ConJson.class)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ConJson save(@RequestBody Company company, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        if (Q.notNull(company) && !Q.notNull(company.getId())) {//不存在id是保存
            if (Q.notNull(companyService.find())){
                String warn_eng = "this table can only apply one row";
                String warn_chn = "此表只能保存至多一条内容";
                return FORBIDDEN(getDescribe(warn_eng, warn_chn));
            }
            company = companyService.save(company);
            return (Q.notNull(company)) ?
                    OK(describe, true, conJson) : OK(describe, false, conJson);
        } else {
            String warn_eng = "save object cannot applied the key:id";
            String warn_chn = "保存对象时不能传入主键id";
            return FORBIDDEN(getDescribe(warn_eng, warn_chn));
        }
    }

}
