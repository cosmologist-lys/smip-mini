package com.smip.controller;

import com.smip.entity.Bscresident;
import com.smip.error.GlobalErrorInfoEnum;
import com.smip.error.GlobalErrorInfoException;
import com.smip.entity.ResultJson;
import com.smip.service.account.BscresidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/resident")
public class BscresidentController {
    @Autowired
    private BscresidentService bscresidentService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(value = "/")
    public List<Bscresident> findAll(){
        List<Bscresident> ps = bscresidentService.findAll();
        System.out.println(ps.size());
        return ps;
    }

    @GetMapping(value = "/query/{id}")
    public ResultJson findOne(@PathVariable("id") Integer id){
        Bscresident person = bscresidentService.findOne(new Integer(id));
        System.out.println(person.toString());
        ResultJson json = new ResultJson(person);
        return json;
    }

    @GetMapping(value = "/name={conditions}")
    public Bscresident findOne(@PathVariable("conditions") String conditions){
        Bscresident person = new Bscresident();
        person.setName(conditions);
        person = bscresidentService.findOne(person);
        System.out.println(person.toString());
        return person;
    }

    @GetMapping(value = "/save")
    @Transactional(rollbackFor = {SpelEvaluationException.class,Exception.class})
    public void save(){
        Bscresident person = new Bscresident();
        person.setName("test");
        person.setCode("ddsf232");
        person.setInstDate(new Date());
        bscresidentService.save(person);
        int s = 3/0;
        Bscresident person1 = new Bscresident();
        person1.setCode("dfsdf23232");
        person1.setName("dfsd");
        bscresidentService.save(person1);
        System.out.println("success");//dsfsf
    }

    @GetMapping(value = "/globalerror")
    public ResultJson testError()  throws GlobalErrorInfoException {
        throw new GlobalErrorInfoException(GlobalErrorInfoEnum.NOT_FOUND);
    }
}
