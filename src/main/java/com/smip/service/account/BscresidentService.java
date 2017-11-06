package com.smip.service.account;

import com.smip.entity.Bscresident;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BscresidentService {

    List<Bscresident> findAll();

    Bscresident findOne(Integer id);

    Bscresident findOne(Bscresident bscresident);

    void save(Bscresident bscresident);
}
