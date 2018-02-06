package com.smip.serviceImpl.basement;

import com.smip.entity.basement.Cardtype;
import com.smip.repository.basement.CardtypeRepository;
import com.smip.service.basement.CardtypeService;
import com.smip.serviceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardtypeServiceImpl extends BaseServiceImpl<Cardtype> implements CardtypeService {
    @Autowired
    private CardtypeRepository cardtypeRepository;
}
