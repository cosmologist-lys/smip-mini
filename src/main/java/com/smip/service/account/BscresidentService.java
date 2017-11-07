package com.smip.service.account;

import com.smip.entity.account.Bscresident;

import java.util.List;

public interface BscresidentService {

    List<Bscresident> findAll();

    Bscresident findOne(Integer id);

    Bscresident findOne(Bscresident bscresident);

    void save(Bscresident bscresident);
}
