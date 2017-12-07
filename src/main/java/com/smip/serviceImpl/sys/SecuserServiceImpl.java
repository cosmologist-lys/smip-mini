package com.smip.serviceImpl.sys;

import com.smip.entity.sys.Secuser;
import com.smip.repository.sys.SecuserRepository;
import com.smip.service.sys.SecuserService;
import com.smip.serviceImpl.BaseServiceImpl;
import com.smip.ulities.Q;
import com.smip.ulities.SysConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SecuserServiceImpl extends BaseServiceImpl<Secuser> implements SecuserService {
    @Autowired
    private SecuserRepository secuserRepository;

    @Override
    @Cacheable(value = "secuser",key = "#username")
    public Secuser findByName(String username) {
        if (Q.notNull(SysConst.SYS_SECUSERS)){
            return SysConst.SYS_SECUSERS.get(username);
        }
        return secuserRepository.findOne(Example.of(new Secuser().setUserName(username)));
    }

    @Override
    @Cacheable(value = "allSecuser")
    public List<Secuser> findAll() {
        return super.findAll();
    }
}
