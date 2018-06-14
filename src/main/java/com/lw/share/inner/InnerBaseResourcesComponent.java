package com.lw.share.inner;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lw.share.entity.BaseResources;
import com.lw.share.mapper.BaseResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘晨
 * @create 2018-05-06 21:40
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Component
public class InnerBaseResourcesComponent {

    @Autowired(required = false)
    private BaseResourcesMapper baseResourcesMapper;

    public Integer addRes(BaseResources baseResources){
        return baseResourcesMapper.insertAllColumn(baseResources);
    }

    public List<BaseResources> selectReses(BaseResources baseResources){
        EntityWrapper<BaseResources> baseResourcesEntityWrapper = new EntityWrapper<>();
        if(baseResources.getId() != null && !baseResources.getId().equals("")){
            baseResourcesEntityWrapper.eq("id",baseResources.getId());
        }
        if(baseResources.getClassId() != null && !baseResources.getClassId().equals("")){
            baseResourcesEntityWrapper.eq("class_id",baseResources.getClassId());
        }
        baseResourcesEntityWrapper.eq("is_delete",baseResources.getIsDelete());
        baseResourcesEntityWrapper.orderBy("top_order");
        return baseResourcesMapper.selectList(baseResourcesEntityWrapper);
    }

    public Integer updateRes(BaseResources baseResources){
        return baseResourcesMapper.updateById(baseResources);
    }
}
