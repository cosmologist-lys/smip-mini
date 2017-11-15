package com.smip.serviceImpl.sys;

import com.smip.entity.sys.Secuser;
import com.smip.repository.sys.SecuserRepository;
import com.smip.service.sys.SecuserService;
import com.smip.serviceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class SecuserServiceImpl extends BaseServiceImpl<Secuser> implements SecuserService {
    @Autowired
    private SecuserRepository secuserRepository;

    @Override
    public Secuser findByName(String username) {
        Secuser user = new Secuser();
        user.setUserName(username);
        Example<Secuser> ex = Example.of(user);
        return secuserRepository.findOne(ex);
    }
}
