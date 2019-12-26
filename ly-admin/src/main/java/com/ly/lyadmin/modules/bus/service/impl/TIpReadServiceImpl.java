package com.ly.lyadmin.modules.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.lyadmin.modules.bus.mapper.TIpReadMapper;
import com.ly.lyadmin.modules.bus.model.TIpRead;
import com.ly.lyadmin.modules.bus.service.TIpReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/12/23 10:08 上午
 * @Version V1.0
 */

@Service
public class TIpReadServiceImpl extends ServiceImpl<TIpReadMapper, TIpRead> implements TIpReadService {

    @Autowired
    TIpReadMapper tIpReadMapper;

    @Override
    public TIpRead findByIdIp(String contentId, String ip) {
        return tIpReadMapper.findByIdIp(contentId, ip);
    }
}
