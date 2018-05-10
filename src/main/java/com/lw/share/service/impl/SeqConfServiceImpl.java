package com.lw.share.service.impl;

import com.lw.share.entity.SeqConf;
import com.lw.share.mapper.SeqConfMapper;
import com.lw.share.service.SeqConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.tools.PkUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 刘晨
 * @create 2018-05-06 22:42
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/
@Service
public class SeqConfServiceImpl implements SeqConfService {

    @Autowired(required = false)
    private SeqConfMapper seqConfMapper;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddss");

    @Override
    @Transactional(readOnly = false,rollbackFor = {Exception.class})
    public String getId(String seqName) {
        String result = "";
        // 查询出指定序列的当前值，并且进行更新
        SeqConf seqConf = seqConfMapper.selectOne(new SeqConf(seqName));
        result = String.format(simpleDateFormat.format(new Date()) + PkUtils.randomNum(3) + "%06d",seqConf.getSeqCurrent());
        //更新当前序列
        seqConf.setSeqCurrent(seqConf.getSeqCurrent() + seqConf.getSeqStep());
        //如果序列值为最大值，则归零
        if(seqConf.getSeqCurrent() > seqConf.getSeqEnd()){
            seqConf.setSeqCurrent(seqConf.getSeqStart());
        }
        seqConfMapper.updateAllColumnById(seqConf);
        return result;
    }
}
