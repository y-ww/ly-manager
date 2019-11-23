package com.ly.lyadmin.modules.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.common.utils.Result;
import com.ly.common.utils.StringUtil;
import com.ly.lyadmin.modules.bus.mapper.TInfoMapper;
import com.ly.lyadmin.modules.bus.model.TInfo;
import com.ly.lyadmin.modules.bus.service.TInfoService;
import com.ly.lyadmin.modules.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/22 12:43 上午
 * @Version V1.0
 */
@Service
public class TInfoServiceImpl extends ServiceImpl<TInfoMapper, TInfo> implements TInfoService {

    @Autowired
    TInfoMapper tInfoMapper;

    @Override
    public Result contentlist(Long userId, Integer pageNo, Integer pageSize, String searchKey, String searchValue) {


        //  根据用户编号 查询出  平台 ID
        String ptCode = "0";

        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<TInfo>();

        queryWrapper.eq("platform",ptCode);
        queryWrapper.eq("personid",userId);

        if(StringUtil.isNotBlank(searchKey)){
            queryWrapper.like(searchKey,searchValue);
        }
        IPage<TInfo> page = new Page<TInfo>(pageNo,pageSize);
        IPage<TInfo> iPage = tInfoMapper.selectPage(page, queryWrapper);
        return Result.ok().put("count",iPage.getTotal()).put("data",iPage.getRecords());

    }
}
