package com.lw.share.service;


import com.lw.share.commons.model.TreeModel;
import com.lw.share.entity.BaseClass;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-06 21:41
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public interface BaseClassService {

    Integer insertBaseClass(BaseClass baseClass);

    List<BaseClass> queryBaseClassesByParentId(String parentId);

    List<TreeModel<BaseClass>> queryBaseClasses4TreeByParentId(String parentId);

    Integer deleteBaseClassById(String id);

    BaseClass queryBaseClassById(String id);

    BaseClass updateBaseClass(BaseClass baseClass);
}
