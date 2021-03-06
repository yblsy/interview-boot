package com.lw.share.controller;

import com.google.gson.Gson;
import com.lw.share.commons.annotation.RedisUpOpr;
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
import personal.enums.RedisKeyEnum;
import personal.tools.RedisUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private Gson gson;

    @RequestMapping("{path}")
    public String to(@PathVariable("path") String path){
        return path;
    }

    @RequestMapping(value = "/home/tomain",method = RequestMethod.GET)
    public String toMain(){
        return "home/main";
    }

    @RequestMapping(value = "tomanage",method = RequestMethod.GET)
    public String toManage(){
        log.info("into manage");
        return "classes/manage";
    }

    @RequestMapping(value = "queryClass",method = {RequestMethod.POST,RequestMethod.GET},produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult queryClass(){
        List<TreeModel<BaseClass>> results = null;
        //当查询全部节点的时候，优先从redis里面拿取数据
        if(redisUtils.hasKey(RedisKeyEnum.IV_MENU.getCode())){
            Map<String,Object> result = redisUtils.getRedisMap(RedisKeyEnum.IV_MENU.getCode());
            results = gson.fromJson(result.get("data").toString(),List.class);
        }else{
            results = baseClassService.queryBaseClasses4TreeByParentId(null,null);
            Map<String,Object> redisValue = new HashMap<>();
            redisValue.put("data",gson.toJson(results));

            redisUtils.setRedisForVersion(RedisKeyEnum.IV_MENU.getCode(),redisValue);
        }
        return InterviewResult.success(results,"查询成功");
    }

    @RequestMapping(value = "insertClass",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @RedisUpOpr(key = RedisKeyEnum.IV_MENU)
    @ResponseBody
    public InterviewResult insertClass(BaseClass baseClass){
        Integer result = baseClassService.insertBaseClass(baseClass);
        return InterviewResult.success(result,"新增成功");
    }

    @RequestMapping(value = "deleteClass",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @RedisUpOpr(key = RedisKeyEnum.IV_MENU)
    @ResponseBody
    public InterviewResult deleteClass(String id){
        Integer result = baseClassService.deleteBaseClassById(id);
        return InterviewResult.success(result,"删除成功");
    }

    @RequestMapping(value = "queryBaseClass",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public InterviewResult queryBaseClass(String id){
        BaseClass result = baseClassService.queryBaseClassById(id);
        return InterviewResult.success(result,"查询成功");
    }

    @RequestMapping(value = "updateBaseClass",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @RedisUpOpr(key = RedisKeyEnum.IV_MENU)
    @ResponseBody
    public InterviewResult updateBaseClass(BaseClass baseClass){
        BaseClass result = baseClassService.updateBaseClass(baseClass);
        return InterviewResult.success(result,"查询成功");
    }
}
