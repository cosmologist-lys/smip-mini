package com.smip.repository.account;

import com.smip.entity.account.Bscresident;
import com.smip.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BscresidentRepository extends BaseRepository<Bscresident>{
    @Query(value = "select b from Bscresident b where b.name like ?1% or b.name = ?1%")
    Bscresident findByNamelike(@Param("name") String name);
}
