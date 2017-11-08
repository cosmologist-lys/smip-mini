package com.smip.controller;

import com.smip.entity.ResultJson;
import com.smip.error.ErrorInfoInterface;
import com.smip.error.GlobalErrorInfoException;
import com.smip.service.BaseService;
import com.smip.ulities.GlobalConstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.smip.ulities.webComponent.getMethodDiscribe;
import static com.smip.ulities.webComponent.getMethodName;

/**
 * Created by kepler@gmail.com on 2017/11/8.
 */
public class BaseController<T> {
    @Autowired
    private BaseService<T> baseService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String discribe;


    /**
     * query:
     */

    public ResultJson querySingleById(Integer id, HttpServletRequest req){
        this.discribe = getMethodDiscribe(getMethodName());
        this.logger.debug(getMethodName(),req.getRequestURI());
        T t = baseService.findOne(id);
        if (null != t)
            return new ResultJson(t,HttpStatus.OK, GlobalConstance.RESULTJSON_TYPE_OBJECT,discribe);
        else
            return new ResultJson(req);
    }

    public ResultJson countAll(HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        int count = baseService.count();
        return new ResultJson(count,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_INTEGER,discribe);
    }

    public ResultJson countByObject(T t,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        int count = baseService.count(t);
        return new ResultJson(count,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_INTEGER,discribe);
    }

    public ResultJson queryListByObject(T t,int page,int size,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        Pageable pageable = new PageRequest(page,size, Sort.Direction.DESC);
        Page<T> t_page = baseService.findListByObject(t,pageable);
        if (null != t_page)
            return new ResultJson(t_page,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_PAGE_OBJECT,discribe);
        else
            return new ResultJson(req);
    }

    /**
     * check exist,boolean
     */
    public ResultJson checkExistById(int id,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        boolean exist = baseService.exist(id);
        if (exist)
            return new ResultJson(exist,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_BOOLEAN,discribe);
        else
            return new ResultJson(req);
    }

    public ResultJson checkExistByObject(T t,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        if (null == t)
            return new ResultJson(req);
        boolean exist = baseService.exist(t);
        if (exist)
            return new ResultJson(exist,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_BOOLEAN,discribe);
        else
            return new ResultJson(req);
    }
    /*
        save object(s),or update object(s)
     */
    public ResultJson saveObject(T t,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        if (null == t)
            return new ResultJson(req);
        T newT = baseService.save(t);
        if (null != newT)
            return new ResultJson(1,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_INTEGER,discribe);
        else
            return new ResultJson(req);
    }

    public ResultJson saveListObject(List<T> ts,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        if (ts.size() == 0)
            return new ResultJson(req);
        List<T> newTs = baseService.save(ts);
        if (newTs.size() > 0)
            return new ResultJson(newTs.size(),HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_INTEGER,discribe);
        else
            return new ResultJson(req);
    }

    /*
        delete object(s)
     */

    public ResultJson deleteObject(int id,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        baseService.deleteOne(id);
        return new ResultJson(1,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_INTEGER,discribe);
    }

    public ResultJson deleteObject(T t,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());
        if (null == t)
            return new ResultJson(req);
        baseService.deleteOne(t);
        return new ResultJson(1,HttpStatus.OK,GlobalConstance.RESULTJSON_TYPE_INTEGER,discribe);
    }

    public ResultJson deleteListByObject(T t,HttpServletRequest req){
        this.logger.debug(getMethodName(),req.getRequestURI());
        this.discribe = getMethodDiscribe(getMethodName());

        return new ResultJson(req);//override then
    }
}
