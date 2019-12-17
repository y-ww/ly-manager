package com.ly.lyadmin.modules.bus.web.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.model.*;
import com.ly.lyadmin.modules.bus.service.*;
import com.ly.lyadmin.modules.bus.utils.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    TContentCarouselService tContentCarouselService;


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
        queryWrapper.eq("id",id);
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
    public Result searchContentByPtCodeId(@RequestParam Integer pageNo,@RequestParam Integer pageSize,
                                          @RequestParam String colid,@RequestParam String ptCode){
        if(colid == "11" || "11".equals(colid)){
            colid = "3";
        }
        Result r =  tInfoService.contentTitleList(pageNo,pageSize,colid,ptCode);
        return r;
    }


   /**
    * @MethodName: 新闻动态列表查询接口不带分页
    * @Description: TODO
    * @Param:
    * @Return:
    * @Author: SLIGHTLEE
    * @Email: lmm_work@163.com
    * @Date: 2019/12/5 11:18 下午
    */
    @ApiOperation(value = "新闻动态列表查询接口不带分页" , notes="新闻动态列表查询接口不带分页")
    @RequestMapping(value = "/sercontentByPtCodeId",method = RequestMethod.POST)
    public Result sercontentByPtCodeId(@RequestParam String colid,@RequestParam String ptCode){
        if(colid == "11" || "11".equals(colid)){
            colid = "3";
        }
        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title","summary","min_pic_address");
        queryWrapper.eq("colid",colid);
        queryWrapper.eq("platform",ptCode);
        queryWrapper.eq("isdelete",Constant.STATUS_ISUSER);
        queryWrapper.eq("is_fbtype",Constant.STATUS_ISUSER);
        queryWrapper.orderByDesc("create_time");
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
        queryWrapper.select("id","title");
        queryWrapper.like("title",title);
        queryWrapper.eq("platform",ptCode);
        queryWrapper.eq("isdelete",Constant.STATUS_ISUSER);
        queryWrapper.eq("is_fbtype",Constant.STATUS_ISUSER);
        queryWrapper.orderByDesc("create_time");
        List<TInfo> tInfoList = tInfoService.list(queryWrapper);
        return Result.ok().put("tInfoList",tInfoList);
    }


    /**
     * @Description: 站内搜索查询接口带分页
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date:
     */
    @ApiOperation(value = "站内搜索查询接口带分页" , notes="站内搜索查询接口带分页")
    @RequestMapping(value = "/searchPage",method = RequestMethod.POST)
    public Result searchPage(@RequestParam Integer pageNo,@RequestParam Integer pageSize,
                             @RequestParam String title,@RequestParam String ptCode){

        Result result = tInfoService.searchTitleList(pageNo, pageSize, title, ptCode);
        return result;
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
        List<TCarousel> carouselList = tCarouselService.list(queryWrapper);
        return Result.ok().put("carouselList",carouselList);
    }

   /**
    * @MethodName: 内容轮播查询接口
    * @Description: TODO
    * @Param:
    * @Return:
    * @Author: SLIGHTLEE
    * @Email: lmm_work@163.com
    * @Date: 2019/12/5 10:06 下午
    */
    @ApiOperation(value = "内容轮播查询接口" , notes="内容轮播查询接口")
    @RequestMapping(value = "/contentCarouselList",method = RequestMethod.POST)
    public Result contentCarouselList(String ptCode){
        QueryWrapper<TContentCarousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pt_code",ptCode);
        queryWrapper.eq("status",Constant.STATUS_ISUSER);
        queryWrapper.orderByDesc("order_num");
        List<TContentCarousel> contentCarouselList = tContentCarouselService.list(queryWrapper);
        return Result.ok().put("contentCarouselList",contentCarouselList);
    }

    /**
     * @MethodName: 联谊会内容介绍接口
     * @Description: TODO
     * @Param: 
     * @Return: 
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/12/5 11:43 下午
     */
    @ApiOperation(value = "联谊会内容介绍接口" , notes="联谊会内容介绍接口")
    @RequestMapping(value = "/sororitylList",method = RequestMethod.GET)
    public Result sororitylList(){
        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id","cbc61b6c5e2b4ca4a208f21b6912c5a3");
        TInfo sororityInfo = tInfoService.getOne(queryWrapper);
        return Result.ok().put("sororityInfo",sororityInfo);
    }

   
    /**
     * @MethodName: 新闻动态内容、上一篇 下一篇查询接口
     * @Description: TODO
     * @Param: 
     * @Return: 
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/12/6 12:41 上午
     */
    @ApiOperation(value = "新闻动态内容、上一篇 下一篇查询接口" , notes="新闻动态内容、上一篇 下一篇查询接口")
    @RequestMapping(value = "/preNextcontent",method = RequestMethod.POST)
    public Result preNextcontent(String id){
        QueryWrapper<TInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("isdelete",Constant.STATUS_ISUSER);
        // 新闻详情
        TInfo tInfo = tInfoService.getById(id);
        // 上一篇
        TInfo preInfo = tInfoService.preContent(id);
        // 下一篇
        TInfo nextInfo = tInfoService.nextContent(id);

        return Result.ok().put("tInfo",tInfo).put("preInfo",preInfo).put("nextInfo",nextInfo);
    }

    /**
     * @Description: 网站动态栏目接口
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/12/17 4:39 下午
     */
    @ApiOperation(value = "网站动态栏目接口" , notes="网站动态栏目接口")
    @RequestMapping(value = "/devColumnList",method = RequestMethod.POST)
    public Result devColumnList(String ptCode){
        QueryWrapper<TColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pt_code",ptCode);
        queryWrapper.eq("show_list",Constant.STATUS_ISUSER);
        List<TColumn> columnList = tColumnService.list(queryWrapper);
        return Result.ok().put("devColumnList",columnList);
    }

}
