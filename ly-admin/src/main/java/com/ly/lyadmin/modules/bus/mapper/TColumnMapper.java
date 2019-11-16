package com.ly.lyadmin.modules.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.lyadmin.modules.bus.model.TColumn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/14 10:10 下午
 * @Version V1.0
 */
@Mapper
public interface TColumnMapper extends BaseMapper<TColumn> {

    /**
     *  平台 ID
     *  根据父菜单，查询子菜单
     */
    List<TColumn> queryListParentId(@Param(value = "parentId") String parentId,@Param(value = "ptCode") String ptCode);


}
