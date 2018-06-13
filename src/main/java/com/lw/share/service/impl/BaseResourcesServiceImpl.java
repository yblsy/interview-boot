package com.lw.share.service.impl;/**
 * Created by liuchen on 2018/5/30.
 */

import com.google.common.base.Strings;
import com.lw.share.commons.enums.SeqConfEnum;
import com.lw.share.entity.BaseResources;
import com.lw.share.inner.InnerBaseResourcesComponent;
import com.lw.share.service.BaseResourcesService;
import com.lw.share.service.SeqConfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.exception.check.ApplicationCheck;
import personal.exception.enums.InterviewErrorEnum;
import personal.exception.exception.InterviewException;

/**
 * @author 刘晨
 * @create 2018-05-30 10:41
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
@Slf4j
public class BaseResourcesServiceImpl implements BaseResourcesService {

    @Autowired(required = false)
    private InnerBaseResourcesComponent innerBaseResourcesComponent;

    @Autowired
    private SeqConfService seqConfService;

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public BaseResources addRes(BaseResources baseResources) {
        //效验资源名称和资源url以及关键词
        ApplicationCheck.checkApplication4NullOrEmpty(baseResources.getResName(),new InterviewException(InterviewErrorEnum.INTER_BR_ER_000004.getCode(),InterviewErrorEnum.INTER_BR_ER_000004.getValue()));
        ApplicationCheck.checkApplication4NullOrEmpty(baseResources.getUrl(),new InterviewException(InterviewErrorEnum.INTER_BR_ER_000006.getCode(),InterviewErrorEnum.INTER_BR_ER_000006.getValue()));
        //关键词三个都需要是空的时候才跳出提示
        ApplicationCheck.checkApplication4NullOrEmpty(baseResources,br -> Strings.isNullOrEmpty(br.getKeyWord01()) && Strings.isNullOrEmpty(br.getKeyWord02()) && Strings.isNullOrEmpty(br.getKeyWord03()),
                new InterviewException(InterviewErrorEnum.INTER_BR_ER_000005.getCode(),InterviewErrorEnum.INTER_BR_ER_000005.getValue()),null,null);
        //获取BaseResources的id
        baseResources.setId(seqConfService.getId(SeqConfEnum.T_BASE_RESOURCES.getCode()));
        Integer result = innerBaseResourcesComponent.addRes(baseResources);
        //效验新增结果
        ApplicationCheck.compareElements(result,1,new InterviewException(InterviewErrorEnum.INTER_BR_ER_000001.getCode(),InterviewErrorEnum.INTER_BR_ER_000001.getValue()));
        return baseResources;
    }
}
