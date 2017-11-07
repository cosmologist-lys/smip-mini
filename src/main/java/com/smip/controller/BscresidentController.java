package com.smip.controller;

import com.smip.entity.account.Bscresident;
import com.smip.error.ErrorInfoInterface;
import com.smip.error.GlobalErrorInfoEnum;
import com.smip.error.GlobalErrorInfoException;
import com.smip.entity.ResultJson;
import com.smip.service.account.BscresidentService;
import com.smip.ulities.GlobalConstance;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/resident")
public class BscresidentController {
    @Autowired
    private BscresidentService bscresidentService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ResultJson json;
    private String discribe;

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public ResultJson getJson() {
        return json;
    }

    public void setJson(ResultJson json) {
        this.json = json;
    }
/*@GetMapping(value = "/")
    public ResultJson findAll(){
        List<Bscresident> ps = bscresidentService.findAll();
        System.out.println(ps.size());
        ResultJson json = new ResultJson(ps, HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_LIST_OBJECT,"test list json");
        return json;
    }

    @GetMapping(value = "/query/{id}")
    @ApiOperation(value = "根据id查询",httpMethod = "GET")
    public ResponseEntity findOne(@PathVariable("id") Integer id){
        Bscresident person = bscresidentService.findOne(new Integer(id));
        System.out.println(person.toString());
        ResultJson json = new ResultJson(person);
        ResponseEntity entity = new ResponseEntity(person, HttpStatus.OK);
        return entity;
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

    @PostMapping(value = "/post")
    public @ResponseBody ResultJson testPost(Bscresident bscresident ){
        System.out.println(bscresident.toString());
        ResultJson json = new ResultJson(bscresident);
        System.out.println(json);
        json.setResult(null);
        json.setDiscribe("post test success");
        return json;
    }*/

    //以下测试swagger2
    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ResultJson findAll(HttpServletRequest req){
        logger.debug(req.getRequestURI(),"findAll()");
        Page<Bscresident> bscresidentList = bscresidentService.findAllByPage(1,30);
        if (bscresidentList != null){
            discribe = "swagger2 test query all";
            json = new ResultJson(bscresidentList,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_LIST_OBJECT,discribe);
        }else{
            json = new ResultJson(req);
        }
        return json;
    }

    @ApiOperation(value="获取单个用户", notes="")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResultJson findOne(@PathVariable("id") Integer id, HttpServletRequest req){
        logger.debug(req.getRequestURI(),"findOne(id)");
        Bscresident bscresident = bscresidentService.findOne(id);
        discribe = "swagger 2 test query one by id";
        if (bscresident == null)
            json = new ResultJson(req);
        else
            json = new ResultJson(bscresident,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_OBJECT,discribe);
        return json;
    }

    @ApiOperation(value="获取单个用户", notes="")
    @RequestMapping(value="/name/{name}", method=RequestMethod.GET)
    public ResultJson findOneByName(@PathVariable("name") String name, HttpServletRequest req){
        logger.debug(req.getRequestURI(),"findOneByName(name)");
        Bscresident bscresident = new Bscresident();
        bscresident.setName(name);
        bscresident = bscresidentService.findOne(bscresident);
        discribe = "find one by name(test example)";
        if (bscresident.getId()>0)
            json = new ResultJson(bscresident,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_OBJECT,discribe);
        else
            json = new ResultJson(req.getRequestURI());
        return json;
    }

    @ApiOperation(value="查询用户个数", notes="")
    @RequestMapping(value="/count", method=RequestMethod.GET)
    public ResultJson count(HttpServletRequest req){
        logger.debug(req.getRequestURI(),"count()");
        int count = bscresidentService.count();
        discribe = "get count of bscresident";
        json = new ResultJson(count,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_INTEGER,discribe);
        return json;
    }

    @ApiOperation(value="查询用户存在", notes="")
    @RequestMapping(value="/exist/{code}", method=RequestMethod.GET)
    public ResultJson exist(@PathVariable("code") String code,HttpServletRequest req){
        boolean exist = false;
        System.out.println("code="+code);
        Bscresident bscresident = new Bscresident();
        bscresident.setCode(code);
        exist = bscresidentService.exist(bscresident);
        json = new ResultJson(exist,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_BOOLEAN,null);
        return json;
    }




}
