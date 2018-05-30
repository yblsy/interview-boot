package com.lw.share.service.impl;/**
 * Created by liuchen on 2018/5/30.
 */

import com.lw.share.entity.BaseResources;
import com.lw.share.inner.InnerBaseResourcesComponent;
import com.lw.share.service.BaseResourcesService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 刘晨
 * @create 2018-05-30 10:41
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
public class BaseResourcesServiceImpl implements BaseResourcesService {

    @Autowired(required = false)
    private InnerBaseResourcesComponent innerBaseResourcesComponent;
}
