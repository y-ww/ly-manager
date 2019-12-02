package com.ly.lyadmin.modules.bus.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.common.utils.RandomUtils;
import com.ly.common.utils.Result;
import com.ly.lyadmin.annotation.SysLog;
import com.ly.lyadmin.modules.bus.model.TColumn;
import com.ly.lyadmin.modules.bus.model.TInfo;
import com.ly.lyadmin.modules.bus.service.TInfoService;
import com.ly.lyadmin.modules.sys.controller.AbstractController;
import com.ly.lyadmin.modules.sys.model.SysUser;
import com.ly.lyadmin.modules.sys.model.SysUserRole;
import com.ly.lyadmin.modules.sys.service.SysUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

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

    @Autowired
    SysUserRoleService sysUserRoleService;


    /**
     * @Description: 添加文章内容
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/11/24 2:13 上午
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(TInfo tInfo){

        // 根据编号查询 文章是否存在 ，存在即 更新 ，不存在 添加
        if(tInfo.getId() != null){
            tInfoService.updateById(tInfo);
        }else{

            System.out.println("tInfo---"+tInfo);
            tInfo.setId(RandomUtils.toFixedLengthStringByUUID(32));
            tInfo.setIsdelete(1);
            tInfo.setCreateTime(new Date());
            SysUser user = getUser();
            tInfo.setPersonid(user.getUserId());
            tInfo.setPlatform(ptCode(user).toString());
            tInfoService.save(tInfo);

        }
        return Result.ok();
    }

    Long ptCode(SysUser user){

        QueryWrapper<SysUserRole> qwrapper = new QueryWrapper<>();
        qwrapper.eq("user_id",user.getUserId());
        SysUserRole sysUserRole = sysUserRoleService.getOne(qwrapper);
        return sysUserRole.getRoleId();
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
    @RequestMapping(value = "/contentlist")
    public Result contentlist(@RequestParam Integer page, @RequestParam Integer limit,
                              @RequestParam(required = false) String searchKey,
                              @RequestParam(required = false) String searchValue){

        SysUser user = getUser();
        Long userId = user.getUserId();

        Result r = tInfoService.contentlist(userId, page, limit, searchKey, searchValue);
        return r;

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
    @RequestMapping(value = "/draftlist")
    public Result draftlist(@RequestParam Integer page, @RequestParam Integer limit,
                              @RequestParam(required = false) String searchKey,
                              @RequestParam(required = false) String searchValue){

        SysUser user = getUser();
        Long userId = user.getUserId();

        Result r = tInfoService.draftlist(userId, page, limit, searchKey, searchValue);
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
    @RequestMapping(value = "/contentInfo",method = RequestMethod.POST)
    public Result contentInfo(String id){
        TInfo tInfo = tInfoService.getById(id);
        return Result.ok().put("data",tInfo);

    }


    /**
     * @Description: 删除文章
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/12/1 3:32 下午
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result delete(@RequestBody String[] ids){

        System.out.println(ids.length);
        boolean result = tInfoService.updateInfoByIds(ids);
        if (result){
            return Result.ok();
        }else{
            return Result.error();
        }

    }
}
