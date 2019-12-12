package com.ly.lyadmin.modules.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.lyadmin.modules.bus.model.TInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TInfoMapper extends BaseMapper<TInfo> {

    int updateInfoByIds(@Param(value = "ids") String[] ids);


    // 上一篇
    TInfo preContent(String id);

    // 下一篇
    TInfo nextContent(String id);
}
