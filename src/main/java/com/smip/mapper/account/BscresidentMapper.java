package com.smip.mapper.account;

import com.smip.entity.account.Bscresident;
import org.apache.ibatis.annotations.Select;

public interface BscresidentMapper {
    @Select("SELECT * FROM bscresident WHERE id >= 500 and tel ='18749795848'")
    Bscresident complexFind();
}
