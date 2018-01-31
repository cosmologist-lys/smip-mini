package com.smip.service.basement;

import com.smip.entity.basement.Company;
import com.smip.service.BaseService;

/**
 * Created by kepler@gmail.com on 2018/1/31.
 */
public interface CompanyService extends BaseService<Company> {
    Company find();
    void update(Company company);
}
