package com.smip.controller.account;


import com.smip.controller.BaseController;
import com.smip.entity.json.FeedbackJson;
import com.smip.entity.json.ReqHeadersMsg;
import com.smip.entity.account.Bscresident;
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
import java.util.List;

@Api(value = "/resident",description = "居民信息",produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/resident")
public class BscresidentController extends BaseController<Bscresident>{
    @Autowired
    private BscresidentService bscresidentService;
    private String describe;


    @Override
    public ReqHeadersMsg beforeController(HttpServletRequest request) {
        return super.beforeController(request);
    }

    @ApiOperation(value="获取单个居民" ,response = FeedbackJson.class)
    @RequestMapping(value="/query/id/{id}", method= RequestMethod.GET)
    public FeedbackJson querySingleById(@PathVariable("id") int id, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident person = bscresidentService.findOne(id);
        if (null != person)
            return OK(describe,person,header);
        else
            return NOTFOUND(describe,header);
    }

    @ApiOperation(value="根据条件获取单个居民" ,response = FeedbackJson.class)
    @RequestMapping(value="/query/one", method= RequestMethod.POST)
    public FeedbackJson querySingleById(@RequestBody Bscresident person, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident p = bscresidentService.findOne(person);
        if (null != p)
            return OK(describe,p,header);
        else
            return NOTFOUND(describe,header);
    }

    @ApiOperation(value="根据name查询居民(模糊匹配)",response = FeedbackJson.class)
    @RequestMapping(value="/query/name/{name}", method=RequestMethod.GET)
    public FeedbackJson queryByNameLike(@PathVariable("name") String name,@ModelAttribute("tokenModel") ReqHeadersMsg header){
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident person = bscresidentService.findByNameLike(name);
        return OK(describe,person,header);
    }

    @ApiOperation(value="查询居民总条数",response = FeedbackJson.class)
    @RequestMapping(value="/count", method=RequestMethod.GET)
    public FeedbackJson countAll(@ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        return OK(describe,header, bscresidentService.count());
    }

    @ApiOperation(value="根据条件查询居民个数",response = FeedbackJson.class)
    @RequestMapping(value="/count/one", method=RequestMethod.POST)
    public FeedbackJson countByObject(@ModelAttribute("tokenModel") ReqHeadersMsg header,@RequestBody Bscresident bscresident) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        int total = bscresidentService.count(bscresident);
        return OK(describe,header,total);
    }

    @ApiOperation(value="根据PAGE查询多个居民",response = FeedbackJson.class)
    @RequestMapping(value="/query/{page}/{size}", method=RequestMethod.GET)
    public FeedbackJson queryListByObject(@PathVariable( "page") int page, @PathVariable("size") int size,@ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Pageable pageable = new PageRequest(page,size, Sort.Direction.DESC,"id");
        Page<Bscresident> persons = bscresidentService.findListByObject(new Bscresident(),pageable);
        return OK(describe,persons,header,persons.getSize());
    }

    @ApiOperation(value="根据PAGE查询多个居民",response = FeedbackJson.class)
    @RequestMapping(value="/query/sort", method=RequestMethod.GET)
    public FeedbackJson queryListByObject_(@RequestParam(value = "page", required = true, defaultValue = "10") Integer page,
                                           @RequestParam(value = "limit", required = true, defaultValue = "12") Integer limit
                                           ,@ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        System.out.println("page,limit="+page+" "+limit);
        Pageable pageable = new PageRequest(page,limit, Sort.Direction.DESC,"id");
        Page<Bscresident> persons = bscresidentService.findListByObject(new Bscresident(),pageable);
        return OK(describe,persons,header,persons.getSize());
    }

    @ApiOperation(value="根据id查找居民是否存在",response = FeedbackJson.class)
    @RequestMapping(value="/exist/{id}", method=RequestMethod.GET)
    public FeedbackJson checkExistById(@PathVariable("id") int id,@ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        boolean flg = bscresidentService.exist(id);
        return OK(describe,header,flg);
    }

    @ApiOperation(value="根据条件查询居民是否存在",response = FeedbackJson.class)
    @RequestMapping(value="/exist/one", method=RequestMethod.POST)
    public FeedbackJson checkExistByObject(@RequestBody Bscresident bscresident, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        boolean flg = bscresidentService.exist(bscresident);
        return OK(describe,header,flg);
    }

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
    }

    @ApiOperation(value="保存单个居民",response = FeedbackJson.class)
    @RequestMapping(value="/save/one", method=RequestMethod.POST)
    public FeedbackJson saveObject(@RequestBody Bscresident bscresident, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        Bscresident person = bscresidentService.save(bscresident);
        if (Q.notNull(person)){
            return OK(describe,person,header);
        }
        return NOTFOUND(describe,header);
    }


    @ApiOperation(value="根据id删除单个居民",response = FeedbackJson.class)
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public FeedbackJson deleteObject(@PathVariable("id") int id, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        bscresidentService.deleteOne(id);
        return OK(describe,null,true);
    }

    @ApiOperation(value="根据条件删除单个居民",response = FeedbackJson.class)
    @RequestMapping(value="/delete/one", method=RequestMethod.DELETE)
    public FeedbackJson deleteObject(@RequestBody Bscresident bscresident, @ModelAttribute("tokenModel") ReqHeadersMsg header) {
        describe = Q_Cpnt.getMethodDiscribe(Q_Cpnt.getMethodName());
        if (!header.isValid()) return FORBIDDEN(header);
        bscresidentService.deleteOne(bscresident);
        return OK(describe,null,true);
    }

}