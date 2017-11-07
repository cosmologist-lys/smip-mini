package com.smip.serviceImpl.account;

import com.smip.entity.account.Bscresident;
import com.smip.repository.account.BscresidentRepository;
import com.smip.service.account.BscresidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BscresidentServiceImpl implements BscresidentService {
    @Autowired
    private BscresidentRepository bscresidentRepository;

    @Override
    public List<Bscresident> findAll() {
        return bscresidentRepository.findAll();
    }

    @Override
    public Bscresident findOne(Integer id) {
        return bscresidentRepository.findOne(id);
    }

    @Override
    public Bscresident findOne(Bscresident bscresident) {
        System.out.println(bscresident.toString());
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.caseSensitive())
                .withIgnorePaths("focus");
        Example<Bscresident> ex = Example.of(bscresident, matcher);
        return bscresidentRepository.findOne(ex);
    }
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    @Override
    public void save(Bscresident bscresident) {
        bscresidentRepository.save(bscresident);
    }
}
