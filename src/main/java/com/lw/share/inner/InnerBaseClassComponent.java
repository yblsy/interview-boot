package com.lw.share.inner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lw.share.entity.BaseClass;
import com.lw.share.mapper.BaseClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-06 21:40
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Component
public class InnerBaseClassComponent {

    @Autowired(required = false)
    private BaseClassMapper baseClassMapper;

    public BaseClass selectOne(String id){
        BaseClass baseClass = new BaseClass();
        baseClass.setId(id);
        return baseClassMapper.selectOne(baseClass);
    }

    public Integer insertBaseClass(BaseClass baseClass){
        return baseClassMapper.insert(baseClass);
    }

    public Integer updateBaseClass(BaseClass baseClass){
        baseClass.setUpdateTime(new Date());
        return baseClassMapper.updateById(baseClass);
    }

    public List<BaseClass> selectAllBaseClasses(){
        EntityWrapper<BaseClass> baseClassEntityWrapper = new EntityWrapper<>();
        baseClassEntityWrapper.eq("is_delete",0);
        return baseClassMapper.selectList(baseClassEntityWrapper);
    }

    public List<BaseClass> selectBaseClassesByParentId(String parentId){
        EntityWrapper<BaseClass> baseClassEntityWrapper = new EntityWrapper<>();
        if(parentId == null){
            baseClassEntityWrapper.eq("level",1);
        }else{
            baseClassEntityWrapper.eq("parent_id",parentId);
        }
        baseClassEntityWrapper.eq("is_delete","0");
        baseClassEntityWrapper.orderBy("sort");
        return baseClassMapper.selectList(baseClassEntityWrapper);
    }
}
