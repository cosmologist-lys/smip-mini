package com.smip.serviceImpl.basement;

import com.smip.entity.basement.Encodesets;
import com.smip.repository.basement.EncodesetsRepository;
import com.smip.service.basement.EncodesetsService;
import com.smip.serviceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kepler@gmail.com on 2018/2/5.
 */

@Service
public class EncodesetsServiceImpl extends BaseServiceImpl<Encodesets> implements EncodesetsService {
    @Autowired
    private EncodesetsRepository encodesetsRepository;


}
