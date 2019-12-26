package com.ly.lyadmin.modules.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.lyadmin.modules.bus.model.TIpRead;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TIpReadMapper extends BaseMapper<TIpRead> {

    TIpRead findByIdIp(@Param(value = "contentId") String contentId,@Param(value = "ip") String ip);
}
