package com.smip.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by kepler@gmail.com on 2017/11/7.
 */
public interface BaseService<T> {
    T findOne(Integer id);
    T findOne(T t);

    List<T> findAll();
    Page<T> findAllByPage(int curPage, int maxPerPage);

    void deleteList(List<T> ts);
    void deleteOne(Integer id);
    void deleteOne(T t);

    int count();
    int count(T t);

    boolean exist(T t);

    T save(T t);
    List<T> save(List<T> t);
}
