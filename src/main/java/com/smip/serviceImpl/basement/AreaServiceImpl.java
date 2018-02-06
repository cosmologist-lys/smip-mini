package com.smip.serviceImpl.basement;

import com.smip.entity.basement.Area;
import com.smip.repository.basement.AreaRepository;
import com.smip.service.basement.AreaService;
import com.smip.serviceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {
    @Autowired
    private AreaRepository areaRepository;
}
