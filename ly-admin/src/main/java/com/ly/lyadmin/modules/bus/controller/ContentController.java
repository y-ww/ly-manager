package com.ly.lyadmin.modules.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.common.utils.RandomUtils;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.model.TColumn;
import com.ly.lyadmin.modules.bus.model.TInfo;
import com.ly.lyadmin.modules.bus.service.TInfoService;
import com.ly.lyadmin.modules.sys.controller.AbstractController;
import com.ly.lyadmin.modules.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/13 1:43 上午
 * @Version V1.0
 * @RestController
 */

@RestController
@RequestMapping("/bus/content")
public class ContentController extends AbstractController {

    @Autowired
    TInfoService tInfoService;

    /**
     * @Description: 添加文章内容
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/11/24 2:13 上午
     */
    @RequestMapping("/save")
    public Result save(TInfo tInfo){
        System.out.println("tInfo---"+tInfo);
        tInfo.setId(RandomUtils.toFixedLengthStringByUUID(32));
        tInfoService.save(tInfo);
        return Result.ok();

    }


    /**
     * @Description:  根据用户编号、平台ID 查询 文章
     *
     *  * 后面需要加上 是否发布 开始结束时间 文章标题 搜索文章
     *
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/11/24 2:13 上午
     */
    @RequestMapping("/contentlist")
    public Result contentlist(@RequestParam Integer page, @RequestParam Integer limit,
                              @RequestParam(required = false) String searchKey,
                              @RequestParam(required = false) String searchValue){

        SysUser user = getUser();
        Long userId = user.getUserId();

        Result r = tInfoService.contentlist(userId, page, limit, searchKey, searchValue);
        return r;

    }

    /**
     * @Description: 根据文章编号 查看文章详情
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/11/24 2:14 上午
     */
    @RequestMapping("/contentInfo")
    public Result contentInfo(String id){
        TInfo tInfo = tInfoService.getById(id);
        return Result.ok().put("data",tInfo);

    }
}
