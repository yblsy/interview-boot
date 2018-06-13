package com.lw.share.inner;

import com.lw.share.entity.BaseResources;
import com.lw.share.mapper.BaseResourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
