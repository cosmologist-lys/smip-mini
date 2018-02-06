package com.smip.serviceImpl;

import com.smip.repository.BaseRepository;
import com.smip.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    public void deleteList(List<T> ts) {

    }

    @Override
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    public void deleteOne(Integer id) {
        baseRepository.delete(id);
    }

    @Override
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    public void deleteOne(T t) {
        baseRepository.delete(t);
    }

    @Override
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
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
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    public T save(T t) {
        return baseRepository.save(t);
    }

    @Override
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    public List<T> save(List<T> ts) {
        return baseRepository.save(ts);
    }

    @Override
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    public Page<T> findListByObject(T t,Pageable pageable) {
        Example<T> ex = Example.of(t);
        return baseRepository.findAll(ex,pageable);
    }

    @Override
    public boolean exist(int id) {
        return baseRepository.exists(id);
    }

    @Override
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    public void update(T t) {
        baseRepository.saveAndFlush(t);
    }

    @Override
    public void update(List<T> ts) {
        baseRepository.save(ts);
    }
}
