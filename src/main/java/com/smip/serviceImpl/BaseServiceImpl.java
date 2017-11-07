package com.smip.serviceImpl;

import com.smip.entity.account.Bscresident;
import com.smip.repository.BaseRepository;
import com.smip.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;

import java.util.List;

/**
 * Created by kepler@gmail.com on 2017/11/7.
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    BaseRepository<T> baseRepository;

    private ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnorePaths("focus")//基本类型
            .withIgnoreCase(true);//忽略大小写

    @Override
    public T findOne(Integer id) {
        return baseRepository.findOne(id);
    }

    @Override
    public T findOne(T t) {
        Example<T> ex = Example.of(t);
        return baseRepository.findOne(ex);
    }


    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Page<T> findAllByPage(int curPage, int maxPerPage) {
        Pageable pageable = new PageRequest(curPage,maxPerPage, Sort.Direction.DESC,"id");
        return baseRepository.findAll(pageable);
    }

    @Override
    public void deleteList(List<T> ts) {

    }

    @Override
    public void deleteOne(Integer id) {

    }

    @Override
    public void deleteOne(T t) {

    }

    @Override
    public int count() {
        return ((int) baseRepository.count());
    }

    @Override
    public int count(T t) {
        Example<T> ex = Example.of(t, matcher);
        return ((int) baseRepository.count(ex));
    }

    @Override
    public boolean exist(T t) {
        Example<T> ex = Example.of(t);
        return baseRepository.exists(ex);
    }

    @Override
    public T save(T t) {
        return null;
    }

    @Override
    public List<T> save(List<T> t) {
        return null;
    }
}
