package com.lw.share.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.lw.share.commons.enums.SeqConfEnum;
import com.lw.share.commons.model.TreeModel;
import com.lw.share.entity.BaseClass;
import com.lw.share.inner.InnerBaseClassComponent;
import com.lw.share.service.BaseClassService;
import com.lw.share.service.SeqConfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.enums.IsDeletedEnum;
import personal.exception.enums.InterviewErrorEnum;
import personal.exception.exception.InterviewException;
import personal.tools.RedisUtils;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-06 21:41
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
@Slf4j
public class BaseClassServiceImpl implements BaseClassService {

    @Autowired
    private InnerBaseClassComponent innerBaseClassComponent;

    @Autowired
    private SeqConfService seqConfService;

    @Autowired(required = false)
    private RedisUtils redisUtils;

    @Autowired(required = false)
    private Gson gson;

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer insertBaseClass(BaseClass baseClass) {

        if(Strings.isNullOrEmpty(baseClass.getName())){
            throw new InterviewException(InterviewErrorEnum.INTER_BC_ER_000004.getCode(), InterviewErrorEnum.INTER_BC_ER_000004.getValue());
        }

        int result = 0;
        BaseClass parentClass = null;
        if(!Strings.isNullOrEmpty(baseClass.getParentId())){
            parentClass = innerBaseClassComponent.selectOne(baseClass.getParentId());
            if(parentClass == null){
                throw new InterviewException(InterviewErrorEnum.INTER_BC_ER_000001.getCode(), InterviewErrorEnum.INTER_BC_ER_000001.getValue());
            }
            //该父节点多增加一个子节点
            parentClass.setChildNum(parentClass.getChildNum() + 1);
            result = innerBaseClassComponent.updateBaseClass(parentClass);
            if(result != 1){
                throw new InterviewException(InterviewErrorEnum.INTER_BC_ER_000002.getCode(),InterviewErrorEnum.INTER_BC_ER_000002.getValue());
            }
        }else{
            parentClass = new BaseClass();
            parentClass.setLevel("0");
            parentClass.setParentName(null);
            parentClass.setParentId(null);
        }

        //增加节点
        baseClass.setId(seqConfService.getId(SeqConfEnum.T_BASE_CLASS.getCode()));
        baseClass.setParentName(parentClass.getParentName());
        baseClass.setLevel("" + (Integer.parseInt(parentClass.getLevel()) + 1));
        baseClass.setParentId(parentClass.getId());
        baseClass.setUrl(Strings.emptyToNull(baseClass.getUrl()));
        baseClass.setMemo(Strings.emptyToNull(baseClass.getMemo()));
        baseClass.setChildNum(0);

        result = innerBaseClassComponent.insertBaseClass(baseClass);
        if(result != 1){
            throw new InterviewException(InterviewErrorEnum.INTER_BC_ER_000003.getCode(),InterviewErrorEnum.INTER_BC_ER_000003.getValue());
        }
        return result;
    }

    @Override
    public List<BaseClass> queryBaseClassesByParentId(String parentId) {
        return innerBaseClassComponent.selectBaseClassesByParentId(Strings.emptyToNull(parentId));
    }

    @Override
    public List<TreeModel<BaseClass>> queryBaseClasses4TreeByParentId(String parentId) {
        List<TreeModel<BaseClass>> results = Lists.newArrayList();
        //所有顶级节点
        List<BaseClass> baseClasses = innerBaseClassComponent.selectBaseClassesByParentId(parentId);
        for (BaseClass baseClass : baseClasses){
            //当前节点
            TreeModel<BaseClass> treeModel = TreeModel.getChildByT(baseClass);
            //查找子节点
            List<TreeModel<BaseClass>> children = this.queryBaseClasses4TreeByParentId(baseClass.getId());
            if(children != null && children.size() > 0){
                treeModel.setNodes(children);
            }
            results.add(treeModel);
        }
        return results;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer deleteBaseClassById(String id) {
        BaseClass baseClass = new BaseClass();
        baseClass.setId(id);
        baseClass.setIsDelete(IsDeletedEnum.HAS_DELETED.getCode());
        int result = innerBaseClassComponent.updateBaseClass(baseClass);
        if(result != 1){
            throw new InterviewException(InterviewErrorEnum.INTER_BC_ER_000005.getCode(),InterviewErrorEnum.INTER_BC_ER_000005.getValue());
        }
        return result;
    }

    @Override
    public BaseClass queryBaseClassById(String id) {
        return innerBaseClassComponent.selectOne(id);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public BaseClass updateBaseClass(BaseClass baseClass) {
        int result = innerBaseClassComponent.updateBaseClass(baseClass);
        if(result != 1){
            throw new InterviewException(InterviewErrorEnum.INTER_BC_ER_000006.getCode(),InterviewErrorEnum.INTER_BC_ER_000006.getValue());
        }
        return innerBaseClassComponent.selectOne(baseClass.getId());
    }
}
