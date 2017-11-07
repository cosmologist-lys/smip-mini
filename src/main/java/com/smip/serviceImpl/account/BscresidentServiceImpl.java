package com.smip.serviceImpl.account;

import com.smip.entity.account.Bscresident;
import com.smip.repository.account.BscresidentRepository;
import com.smip.service.account.BscresidentService;
import com.smip.serviceImpl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BscresidentServiceImpl extends BaseServiceImpl<Bscresident> implements BscresidentService {
    @Autowired
    private BscresidentRepository bscresidentRepository;

    @Override
    public Bscresident findOne(Integer id) {
        return super.findOne(id);
    }

    @Override
    public Bscresident findOne(Bscresident bscresident) {
        return super.findOne(bscresident);
    }

    @Override
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
        super.deleteOne(bscresident);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public int count(Bscresident bscresident) {
        return super.count(bscresident);
    }

    @Override
    public boolean exist(Bscresident bscresident) {
        return super.exist(bscresident);
    }

    @Override
    public Bscresident save(Bscresident bscresident) {
        return super.save(bscresident);
    }

    @Override
    public List<Bscresident> save(List<Bscresident> t) {
        return super.save(t);
    }
}
