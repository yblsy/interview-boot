package com.lw.share.controller;/**
 * Created by liuchen on 2018/5/30.
 */

import com.lw.share.commons.model.InterviewResult;
import com.lw.share.entity.BaseResources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 刘晨
 * @create 2018-05-30 11:01
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Controller
@RequestMapping("/res/")
@Slf4j
public class BaseResourcesController {

    @RequestMapping("queryResByClass")
    public InterviewResult queryResByClass(String classId){
        return null;
    }

    @RequestMapping("addRes")
    public InterviewResult addRes(BaseResources baseResources){
        return null;
    }

    @RequestMapping("upRes")
    public InterviewResult upRes(BaseResources baseResources){
        return null;
    }

    @RequestMapping("delRes")
    public InterviewResult delRes(String id){
        return null;
    }
}
