package com.ly.lyadmin.modules.bus.web.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.model.TCarousel;
import com.ly.lyadmin.modules.bus.model.TColumn;
import com.ly.lyadmin.modules.bus.model.TDict;
import com.ly.lyadmin.modules.bus.model.TInfo;
import com.ly.lyadmin.modules.bus.service.TCarouselService;
import com.ly.lyadmin.modules.bus.service.TColumnService;
import com.ly.lyadmin.modules.bus.service.TDictService;
import com.ly.lyadmin.modules.bus.service.TInfoService;
import com.ly.lyadmin.modules.bus.utils.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/14 10:33 下午
 * @Version V1.0
 */
@Api(description = "网站访问接口")
@RestController
@RequestMapping("/web")
public class TWebController {

    @Autowired
    TColumnService tColumnService;

    @Autowired
    TDictService tDictService;

    @Autowired
    TInfoService tInfoService;

    @Autowired
    TCarouselService tCarouselService;


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
    @ApiOperation(value = "网站栏目菜单接口" , notes="网站栏目菜单接口")
    @RequestMapping(value = "/indexlist",method = RequestMethod.POST)
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

    /**
     * @Description: 十大平台列表接口
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/11/22 12:18 上午
     */
    @ApiOperation(value = "十大平台列表接口" , notes="十大平台列表接口")
    @RequestMapping(value = "/sdptList",method = RequestMethod.POST)
    public Result sdptList(String keyname){

        QueryWrapper<TDict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("keyname",keyname);
        queryWrapper.eq("status",Constant.STATUS_ISUSER);
        queryWrapper.orderByAsc("keydesc");
        List<TDict> tDictList = tDictService.list(queryWrapper);
        return Result.ok().put("tDictList",tDictList);
    }

    /**
     * @Description: 根据内容编号查询接口
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date:
     */
    @ApiOperation(value = "新闻动态内容查询接口" , notes="新闻动态内容查询接口")
    @RequestMapping(value = "/searchContentById",method = RequestMethod.POST)
    public Result searchContentById(String id){
        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("id",id);
        queryWrapper.eq("isdelete",Constant.STATUS_ISUSER);
        List<TInfo> tInfoList = tInfoService.list(queryWrapper);
        return Result.ok().put("tInfoList",tInfoList);
    }


    /**
     * @Description: 根据 平台编号 栏目编号 查询 文章 列表
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date:
     */
    @ApiOperation(value = "新闻动态列表查询接口" , notes="新闻动态列表查询接口")
    @RequestMapping(value = "/searchContentByPtCodeId",method = RequestMethod.POST)
    public Result searchContentByPtCodeId(String colid,String ptCode){
        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("colid",colid);
        queryWrapper.like("platform",ptCode);
        queryWrapper.eq("isdelete",Constant.STATUS_ISUSER);
        List<TInfo> tInfoList = tInfoService.list(queryWrapper);
        return Result.ok().put("tInfoList",tInfoList);
    }


    /**
     * @Description: 站内搜索查询接口
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date:
     */
    @ApiOperation(value = "站内搜索查询接口" , notes="站内搜索查询接口")
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result search(String title,String ptCode){
        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",title);
        queryWrapper.eq("platform",ptCode);
        queryWrapper.eq("isdelete",Constant.STATUS_ISUSER);
        queryWrapper.eq("is_fbtype",Constant.STATUS_ISUSER);
        queryWrapper.orderByDesc("create_time");
        List<TInfo> tInfoList = tInfoService.list(queryWrapper);
        return Result.ok().put("tInfoList",tInfoList);
    }

    /**
     * @Description: 轮播查询接口
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date:
     */
    @ApiOperation(value = "轮播查询接口" , notes="轮播查询接口")
    @RequestMapping(value = "/carouselList",method = RequestMethod.POST)
    public Result carouselList(String ptCode){
        QueryWrapper<TCarousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pt_code",ptCode);
        queryWrapper.eq("status",Constant.STATUS_ISUSER);
        queryWrapper.orderByDesc("order_num");
        List<TCarousel> tInfoList = tCarouselService.list(queryWrapper);
        return Result.ok().put("tInfoList",tInfoList);
    }

}
