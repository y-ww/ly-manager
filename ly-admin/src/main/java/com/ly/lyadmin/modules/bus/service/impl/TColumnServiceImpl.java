package com.ly.lyadmin.modules.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.mapper.TColumnMapper;
import com.ly.lyadmin.modules.bus.model.TColumn;
import com.ly.lyadmin.modules.bus.model.TInfo;
import com.ly.lyadmin.modules.bus.service.TColumnService;
import com.ly.lyadmin.modules.bus.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/14 10:13 下午
 * @Version V1.0
 */
@Service
public class TColumnServiceImpl extends ServiceImpl<TColumnMapper,TColumn> implements TColumnService {

    @Autowired
    TColumnMapper tColumnMapper;

    @Override
    public List<TColumn> queryListParentId(String parentId,String ptCode) {
        return tColumnMapper.queryListParentId(parentId,ptCode);
    }

    @Override
    public Result columnList(Integer pageNo, Integer pageSize,Long roleId) {

        QueryWrapper<TColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pt_code",roleId);

        queryWrapper.eq("status", Constant.STATUS_ISUSER);
        queryWrapper.isNotNull("show_list");

        if(roleId == 0 || "0".equals(roleId)){
            return Result.ok().put("count",0).put("data",null);
        }

        IPage<TColumn> page = new Page<TColumn>(pageNo,pageSize);
        IPage<TColumn> iPage = tColumnMapper.selectPage(page, queryWrapper);
        return Result.ok().put("count",iPage.getTotal()).put("data",iPage.getRecords());
    }
}
