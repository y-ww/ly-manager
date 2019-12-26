package com.ly.lyadmin.modules.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.lyadmin.modules.bus.model.TIpRead;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/12/23 10:07 上午
 * @Version V1.0
 */
public interface TIpReadService extends IService<TIpRead> {

    TIpRead findByIdIp(String contentId,String ip);
}
