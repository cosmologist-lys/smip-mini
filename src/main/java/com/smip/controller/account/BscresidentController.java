package com.smip.controller.account;


import com.smip.controller.BaseController;
import com.smip.entity.account.Bscresident;
import com.smip.entity.json.ConJson;
import com.smip.service.account.BscresidentService;
import com.smip.ulities.Q;
import com.smip.ulities.Q_Cpnt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "/resident", description = "居民信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/resident")
public class BscresidentController extends BaseController<Bscresident> {
    @Autowired
    private BscresidentService bscresidentService;
    private String describe;

    @Override
    public ConJson beforeController(HttpServletRequest request) {
        return super.beforeController(request);
    }

    @ApiOperation(value = "获取单个居民", response = ConJson.class)
    @RequestMapping(value = "/query/id/{id}", method = RequestMethod.GET)
    public ConJson findOneById(@PathVariable("id") int id, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, bscresidentService.findOne(id), conJson);
    }

    @ApiOperation(value = "根据条件获取单个居民", response = ConJson.class)
    @RequestMapping(value = "/query/one", method = RequestMethod.POST)
    public ConJson findOneByCondition(@RequestBody Bscresident person, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, bscresidentService.findOne(person), conJson);
    }

    @ApiOperation(value = "根据name查询居民(模糊匹配)", response = ConJson.class)
    @RequestMapping(value = "/query/name/{name}", method = RequestMethod.GET)
    public ConJson queryByNameLike(@PathVariable("name") String name, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, bscresidentService.findByNameLike(name), conJson);
    }

    @ApiOperation(value = "查询居民总条数", response = ConJson.class)
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ConJson countAll(@ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, bscresidentService.count(), conJson);
    }

    @ApiOperation(value = "根据条件查询居民个数", response = ConJson.class)
    @RequestMapping(value = "/count/one", method = RequestMethod.POST)
    public ConJson countByConditions(@RequestBody Bscresident bscresident, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, bscresidentService.count(bscresident), conJson);
    }

    @ApiOperation(value = "根据PAGE查询多个居民", response = ConJson.class)
    @RequestMapping(value = "/query/{page}/{size}", method = RequestMethod.GET)
    public ConJson findManyByPage(@PathVariable("page") int page, @PathVariable("size") int size, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Bscresident> persons = bscresidentService.findListByObject(new Bscresident(), pageable);
        return OK(describe, persons, conJson);
    }

    @ApiOperation(value = "根据PAGE查询多个居民", response = ConJson.class)
    @RequestMapping(value = "/query/sort", method = RequestMethod.GET)
    public ConJson findManyByPageParam(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", required = true, defaultValue = "12") Integer limit,
                                       @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        System.out.println("page,limit=" + page + " " + limit);
        Pageable pageable = new PageRequest(page, limit, Sort.Direction.DESC, "id");
        Page<Bscresident> persons = bscresidentService.findListByObject(new Bscresident(), pageable);
        return OK(describe, persons, conJson);
    }

    @ApiOperation(value = "根据id查找居民是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/{id}", method = RequestMethod.GET)
    public ConJson isExistById(@PathVariable("id") int id, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, bscresidentService.exist(id), conJson);
    }

    @ApiOperation(value = "根据条件查询居民是否存在", response = ConJson.class)
    @RequestMapping(value = "/exist/one", method = RequestMethod.POST)
    public ConJson isExistByCondition(@RequestBody Bscresident bscresident, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        return OK(describe, bscresidentService.exist(bscresident), conJson);
    }

    @ApiOperation(value = "保存单个居民", response = ConJson.class)
    @RequestMapping(value = "/save/one", method = RequestMethod.POST)
    public ConJson saveOne(@RequestBody Bscresident bscresident, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        Bscresident person = bscresidentService.save(bscresident);
        if (Q.notNull(person)) {
            return OK(describe, true, conJson);
        } else {
            return OK(describe, false, conJson);
        }
    }

    @ApiOperation(value = "根据id删除单个居民", response = ConJson.class)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ConJson deleteOneById(@PathVariable("id") int id, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        bscresidentService.deleteOne(id);
        return OK(describe, true, conJson);
    }

    @ApiOperation(value = "根据条件删除单个居民", response = ConJson.class)
    @RequestMapping(value = "/delete/one", method = RequestMethod.DELETE)
    public ConJson deleteObject(@RequestBody Bscresident bscresident, @ModelAttribute("tokenModel") ConJson conJson) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!conJson.getKeycore().is_isvalid()) return FORBIDDEN();
        bscresidentService.deleteOne(bscresident);
        return OK(describe, true, conJson);
    }
    /*@Override

    @ApiOperation(value="根据条件查询居民",response = FeedbackJson.class)
    @RequestMapping(value="/query/condition", method=RequestMethod.GET)
    public FeedbackJson complexQuery(HttpServletRequest req, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident person = new Bscresident();
        if (Q.notNull(req)){
            String code = req.getParameter("code");
            String tel = req.getParameter("tel");
            System.out.println("code="+code+" tel="+tel);
            person = bscresidentService.complexFind(code,tel);
        }
        return OK(describe,person,header);
    } */

}