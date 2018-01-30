package com.smip.serviceImpl.account;

import com.smip.entity.account.Bscresident;
import com.smip.repository.account.BscresidentRepository;
import com.smip.service.account.BscresidentService;
import com.smip.serviceImpl.BaseServiceImpl;
import com.smip.ulities.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BscresidentServiceImpl extends BaseServiceImpl<Bscresident> implements BscresidentService {
    @Autowired
    private BscresidentRepository bscresidentRepository;
    /*private BscresidentMapper bscresidentMapper;
    public BscresidentServiceImpl(BscresidentMapper bscresidentMapper) {
        this.bscresidentMapper = bscresidentMapper;
    }*/


    @Override
    @Cacheable(value = "person")
    public Bscresident findOne(Integer id) {
        return super.findOne(id);
    }

    @Override
    public Bscresident findOne(Bscresident bscresident) {
        return super.findOne(bscresident);
    }

    @Override
    @Cacheable(value = "personList")
    public List<Bscresident> findAll() {
        return super.findAll();
    }

    @Override
    public Page<Bscresident> findAllByPage(int curPage, int maxPerPage) {
        return super.findAllByPage(curPage, maxPerPage);
    }

    @Override
    public void deleteList(List<Bscresident> bscresidents) {
        super.deleteList(bscresidents);
    }

    @Override
    public void deleteOne(Integer id) {
        super.deleteOne(id);
    }

    @Override
    public void deleteOne(Bscresident bscresident) {
        if (Q.notNull(bscresident)) {
            super.deleteOne(bscresident);
        }
    }

    @Override
    @Cacheable(value = "personCountAll")
    public int count() {
        return super.count();
    }

    @Override
    @Cacheable(value = "personCount")
    public int count(Bscresident bscresident) {
        return super.count(bscresident);
    }

    @Override
    public boolean exist(Bscresident bscresident) {
        return super.exist(bscresident);
    }

    @Override
    @Cacheable(value = "pagePerson", key = "#root.args[1]")
    //key=#root.args[index] 当前方法参数组成的数组组成KEY
    public Page<Bscresident> findListByObject(Bscresident bscresident, Pageable pageable) {
        return super.findListByObject(bscresident, pageable);
    }

    @Override
    @Cacheable(value = "personExistFlg")
    public boolean exist(int id) {
        return super.exist(id);
    }

    @Override
    public Bscresident save(Bscresident bscresident) {
        return super.save(bscresident);
    }

    @Override
    public List<Bscresident> save(List<Bscresident> t) {
        return super.save(t);
    }

    @Override
    @Cacheable(value = "person_byname")
    public Bscresident findByNameLike(String name) {
        return bscresidentRepository.findByNamelike(name);
    }

    @Override
    public Bscresident complexFind(String code, String tel) {
        return bscresidentRepository.complexFind(code, tel);
    }

    /*
    guava:cacheable 更多用法
    有的时候我们可能并不希望缓存一个方法所有的返回结果。
    通过condition属性可以实现这一功能。
    condition属性默认为空，表示将缓存所有的调用情形。
    其值是通过SpringEL表达式来指定的，当为true时表示进行缓存处理；
    当为false时表示不进行缓存处理，即每次调用该方法时该方法都会执行一次。
    如下示例表示只有当user的id为偶数时才会进行缓存。

    @Cacheable(value={"users"}, key="#user.id", condition="#user.id%2==0")

    public User find(User user) {

        System.out.println("find user by user " + user);

        return user;

    }*/

}
