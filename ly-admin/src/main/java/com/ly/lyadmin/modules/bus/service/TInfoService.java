package com.ly.lyadmin.modules.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.model.TInfo;
import com.ly.lyadmin.modules.sys.model.SysUser;

import java.util.List;

public interface TInfoService extends IService<TInfo> {

    Result contentlist(Long userId,Integer pageNo, Integer pageSize, String searchKey, String searchValue);

    Result draftlist(Long userId,Integer pageNo, Integer pageSize, String searchKey, String searchValue);

    boolean updateInfoByIds(String[] ids);
}
