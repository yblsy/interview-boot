package com.lw.share.service.impl;

import com.google.common.base.Strings;
import com.lw.share.commons.enums.ErrorEnum;
import com.lw.share.commons.enums.SeqConfEnum;
import com.lw.share.commons.exceptions.InterviewException;
import com.lw.share.entity.BaseClass;
import com.lw.share.inner.InnerBaseClassComponent;
import com.lw.share.service.BaseClassService;
import com.lw.share.service.SeqConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-06 21:41
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
public class BaseClassServiceImpl implements BaseClassService {

    @Autowired
    private InnerBaseClassComponent innerBaseClassComponent;

    @Autowired
    private SeqConfService seqConfService;

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer insertBaseClass(BaseClass baseClass) {
        int result = 0;
        BaseClass parentClass = null;
        if(!Strings.isNullOrEmpty(baseClass.getParentId())){
            parentClass = innerBaseClassComponent.selectOne(baseClass.getParentId());
            if(parentClass == null){
                throw new InterviewException(ErrorEnum.INTER_BC_ER_000001.getCode(), ErrorEnum.INTER_BC_ER_000001.getValue());
            }
            //该父节点多增加一个子节点
            parentClass.setChildNum(parentClass.getChildNum() + 1);
            result = innerBaseClassComponent.updateBaseClass(parentClass);
            if(result != 1){
                throw new InterviewException(ErrorEnum.INTER_BC_ER_000002.getCode(),ErrorEnum.INTER_BC_ER_000002.getValue());
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

        result = innerBaseClassComponent.insertBaseClass(baseClass);
        if(result != 1){
            throw new InterviewException(ErrorEnum.INTER_BC_ER_000003.getCode(),ErrorEnum.INTER_BC_ER_000003.getValue());
        }
        return result;
    }

    @Override
    public List<BaseClass> queryBaseClassesByParentId(String parentId) {
        return innerBaseClassComponent.selectBaseClassesByParentId(Strings.emptyToNull(parentId));
    }
}
