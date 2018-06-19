package com.lw.share.commons.aop;/**
 * Created by liuchen on 2018/5/28.
 */

import com.google.gson.Gson;
import com.lw.share.commons.annotation.RedisUpOpr;
import com.lw.share.commons.model.TreeModel;
import com.lw.share.entity.BaseClass;
import com.lw.share.service.BaseClassService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import personal.enums.RedisKeyEnum;
import personal.tools.RedisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘晨
 * @create 2018-05-28 10:39
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Component
@Aspect
@Slf4j
public class UpRedisAop {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private Gson gson;

    @Autowired
    private BaseClassService baseClassService;


    @After("within(com.lw.share.controller..*) && @annotation(redisUpOpr)")
    @Async
    public void after(JoinPoint joinPoint,RedisUpOpr redisUpOpr){
        if(redisUpOpr.key().equals(RedisKeyEnum.IV_MENU)){
            log.info("开始更新菜单节点redis,启用的线程名"+Thread.currentThread().getName());
            //查询出所有节点
            List<TreeModel<BaseClass>> data = baseClassService.queryBaseClasses4TreeByParentId(null,null);
            if(redisUtils.hasKey(RedisKeyEnum.IV_MENU.getCode())){
                Map<String,Object> redisMap = redisUtils.getRedisMap(RedisKeyEnum.IV_MENU.getCode());
                redisMap.put("data",gson.toJson(data));
                redisUtils.setRedisForVersion(RedisKeyEnum.IV_MENU.getCode(),redisMap);
            }else{
                redisUtils.setRedisForVersion(RedisKeyEnum.IV_MENU.getCode(),new HashMap<String,Object>(){{ put("data",gson.toJson(data)); }});
            }
            log.info("结束更新菜单节点redis");
        }
    }
}
