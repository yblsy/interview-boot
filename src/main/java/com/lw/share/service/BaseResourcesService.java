package com.lw.share.service;

import com.lw.share.entity.BaseResources;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-06 21:41
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public interface BaseResourcesService {
    BaseResources selectOne(String id);

    BaseResources addRes(BaseResources baseResources);

    List<BaseResources> selectResByClassId(String classId);

    BaseResources updateResById(BaseResources baseResources);

    Integer deleteResById(String id);
}
