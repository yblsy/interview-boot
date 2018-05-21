package com.lw.share.controller;

import com.lw.share.commons.model.InterviewResult;
import com.lw.share.commons.model.TreeModel;
import com.lw.share.entity.BaseClass;
import com.lw.share.service.BaseClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-06 22:04
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Controller
@RequestMapping("/class/")
@Slf4j
public class BaseClassController{

    @Autowired
    private BaseClassService baseClassService;

    @RequestMapping("{path}")
    public String to(@PathVariable("path") String path){
        return path;
    }

    @RequestMapping(value = "tomanage",method = RequestMethod.GET)
    public String toManage(){
        return "classes/manage";
    }

    @RequestMapping(value = "queryClass",method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult queryClass(){
        List<TreeModel<BaseClass>> results = baseClassService.queryBaseClasses4TreeByParentId(null);
        return InterviewResult.success(results,"查询成功");
    }

    @RequestMapping(value = "insertClass",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult insertClass(BaseClass baseClass){
        Integer result = baseClassService.insertBaseClass(baseClass);
        return InterviewResult.success(result,"新增成功");
    }

    @RequestMapping(value = "deleteClass",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult deleteClass(String id){
        Integer result = baseClassService.deleteBaseClassById(id);
        return InterviewResult.success(result,"删除成功");
    }
}
