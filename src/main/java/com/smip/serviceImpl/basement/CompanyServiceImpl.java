package com.smip.serviceImpl.basement;

import com.smip.entity.basement.Company;
import com.smip.repository.basement.CompanyRepository;
import com.smip.service.basement.CompanyService;
import com.smip.serviceImpl.BaseServiceImpl;
import com.smip.ulities.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl  extends BaseServiceImpl<Company> implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company find() {
        if (Q.notNull(super.findAll()))
            return super.findAll().get(0);
        else{
            return companyRepository.findAll().get(0);
        }
    }

    @Override
    public void update(Company company) {
        if (Q.notNull(company.getId())){
            companyRepository.saveAndFlush(company);
        }
    }
}
