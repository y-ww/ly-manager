package com.ly.lyadmin.modules.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.model.TColumn;

import java.util.List;

public interface TColumnService extends IService<TColumn> {

    /**
     *  根据父菜单，查询子菜单
     */
    List<TColumn> queryListParentId(String parentId,String ptCode);

    /**
     * 栏目列表显示
     */
    Result columnList(Integer pageNo, Integer pageSize, Long roleId);

}
