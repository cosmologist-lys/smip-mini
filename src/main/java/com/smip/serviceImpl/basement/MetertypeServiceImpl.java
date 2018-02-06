package com.smip.serviceImpl.basement;

import com.smip.entity.basement.Metertype;
import com.smip.repository.basement.MetertypeRepository;
import com.smip.service.basement.MetertypeService;
import com.smip.serviceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetertypeServiceImpl extends BaseServiceImpl<Metertype> implements MetertypeService {
    @Autowired
    private MetertypeRepository metertypeRepository;
}
