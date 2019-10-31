package com.ly.lyadmin.sys.controller;

import com.ly.common.utils.Result;
import com.ly.lyadmin.annotation.SysLog;
import com.ly.lyadmin.sys.model.SysUser;
import com.ly.lyadmin.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 用户信息
 *
 * @Author: SLIGHTLEE
 * @Date: 2019/10/15 10:47 下午
 *
 */
@Api(description = "用户信息管理")
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController{


    private final SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     *  用户信息
     * @return userList
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Integer page, @RequestParam Integer limit,
                       @RequestParam(required = false) String searchKey,
                       @RequestParam(required = false) String searchValue){
        Result r = sysUserService.getAllUser(page, limit, searchKey, searchValue);
        return r;
    }

    /**
     *   添加用户
     */
    @SysLog("添加用户")
    @ApiOperation(value = "添加用户" , notes="添加用户")
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody SysUser sysUser){

        sysUserService.add(sysUser);

        return Result.ok();
    }


    /**
     *   修改用户
     */
    @SysLog("修改用户")
    @ApiOperation(value = "修改用户" , notes="修改用户")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody SysUser sysUser){
        sysUserService.updateById(sysUser);
        return Result.ok();
    }


    /**
     *   删除用户
     */
    @SysLog("删除用户")
    @ApiOperation(value = "删除用户" , notes="删除用户")
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Result delete(@RequestBody Long[] userIds){
        if(ArrayUtils.contains(userIds,1L)){
            return Result.error("系统管理员不能删除");
        }
        if(ArrayUtils.contains(userIds, getUserId())){
            return Result.error("当前用户不能删除");
        }
        sysUserService.removeByIds(Arrays.asList(userIds));
        return Result.ok();
    }

    /**
     *  获取登录的用户信息
     */
    @RequestMapping("/info")
    public Result info(){
        return Result.ok().put("user",getUser());
    }

    /**
     *  用户信息 不分页
     * @return userList
     */
    @ApiOperation(value = "用户信息" , notes="用户信息")
    @RequestMapping(value = "/userlist",method = RequestMethod.GET)
    public Result list(){
        List<SysUser> list = sysUserService.list();
        return Result.ok().put("list",list);
    }

}
