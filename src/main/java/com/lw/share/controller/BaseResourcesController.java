package com.lw.share.controller;/**
 * Created by liuchen on 2018/5/30.
 */

import com.lw.share.commons.model.InterviewResult;
import com.lw.share.entity.BaseResources;
import com.lw.share.service.BaseResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-30 11:01
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Controller
@RequestMapping("/res/")
@Slf4j
public class BaseResourcesController {

    @Autowired
    private BaseResourcesService baseResourcesService;

    @RequestMapping(value = "queryOneRes", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult queryOneRes(String id){
        return InterviewResult.success(baseResourcesService.selectOne(id));
    }

    @RequestMapping(value = "queryResByClass", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult queryResByClass(String classId){
        List<BaseResources> result = baseResourcesService.selectResByClassId(classId);
        return InterviewResult.success(result);
    }

    @RequestMapping(value = "addRes",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult addRes(BaseResources baseResources){
        return InterviewResult.success(baseResourcesService.addRes(baseResources));
    }

    @RequestMapping(value = "upRes",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult upRes(BaseResources baseResources){
        return InterviewResult.success(baseResourcesService.updateResById(baseResources));
    }

    @RequestMapping(value = "delRes",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult delRes(String id){
        return InterviewResult.success(baseResourcesService.deleteResById(id));
    }
}
