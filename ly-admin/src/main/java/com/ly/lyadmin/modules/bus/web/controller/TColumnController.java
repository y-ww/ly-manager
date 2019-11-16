package com.ly.lyadmin.modules.bus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.model.TColumn;
import com.ly.lyadmin.modules.bus.service.TColumnService;
import com.ly.lyadmin.modules.bus.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/14 10:33 下午
 * @Version V1.0
 */
@RestController
@RequestMapping("/web/tc")
public class TColumnController {

    @Autowired
    TColumnService tColumnService;


    /**
     * @Description:
     *  网站 一级菜单ID 为 o
     *      二级为一级菜单的ID
     *
     * @Param:  父id
     * @Return: 平台id
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/11/16 4:47 下午
     */
    @RequestMapping("/indexlist")
    public Result indexlist(String parentId,String ptCode){
        // 首页网站
        QueryWrapper<TColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pt_code",ptCode);
        queryWrapper.eq("parent_id",parentId);
        queryWrapper.eq("status",Constant.STATUS_ISUSER);
        queryWrapper.orderByAsc("order_num");
        List<TColumn> list = tColumnService.list(queryWrapper);
        return Result.ok().put("indexlist",list);

    }

}
