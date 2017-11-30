package com.smip.service.account;

import com.smip.entity.account.Bscresident;
import com.smip.service.BaseService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BscresidentService extends BaseService<Bscresident> {
    Bscresident findByNameLike(String name);
    Bscresident complexFind(String code,String tel);
}
