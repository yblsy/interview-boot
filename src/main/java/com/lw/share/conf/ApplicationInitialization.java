package com.lw.share.conf;/**
 * Created by liuchen on 2018/5/22.
 */

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.lw.share.commons.model.TreeModel;
import com.lw.share.entity.BaseClass;
import com.lw.share.service.BaseClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Component;
import personal.enums.RedisKeyEnum;
import personal.tools.RedisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘晨
 * @create 2018-05-22 14:58
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Component
@Slf4j
public class ApplicationInitialization implements ApplicationRunner, Ordered {

    @Autowired
    private BaseClassService baseClassService;

    @Autowired(required = false)
    private Gson gson;

    @Autowired(required = false)
    private RedisUtils redisUtils;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //查询出所有节点
        List<TreeModel<BaseClass>> result = baseClassService.queryBaseClasses4TreeByParentId(null,null);
        redisUtils.setRedisForVersion(RedisKeyEnum.IV_MENU.getCode(),new HashMap<String,Object>(){{ put("data",gson.toJson(result)); }});
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
