package com.smip.serviceImpl.basement;

import com.smip.entity.basement.Pricetype;
import com.smip.repository.basement.PricetypeRepository;
import com.smip.service.basement.PricetypeService;
import com.smip.serviceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricetypeServiceImpl extends BaseServiceImpl<Pricetype> implements PricetypeService {
    @Autowired
    private PricetypeRepository pricetypeRepository;
}
