package com.smip.repository.account;

import com.smip.entity.account.Bscresident;
import com.smip.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BscresidentRepository extends BaseRepository<Bscresident>{
    //nativceQuery = true 开启sql查询。false 开启hql查询

    /*
    json对象查询 mongodb可用
    @Query("{'owner.$id': ?#{[0]}, 'enabled': ?#{[1]}, 'archived': ?#{[2]}}")
    Set<Project> findRelated(User user, boolean enabled, boolean archived)*/


    @Query(value = "select b from Bscresident b where b.name like ?1% or b.name = ?1%")
    Bscresident findByNamelike(@Param("name") String name);


    @Query(value = "SELECT b FROM Bscresident b where b.code= :code and b.tel=:tel")
    Bscresident complexFind(@Param("code") String code,@Param("tel") String tel);

}
