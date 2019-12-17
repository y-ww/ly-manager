package com.ly.lyadmin.modules.bus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.model.TColumn;
import com.ly.lyadmin.modules.bus.service.TColumnService;
import com.ly.lyadmin.modules.bus.utils.Constant;
import com.ly.lyadmin.modules.sys.controller.AbstractController;
import com.ly.lyadmin.modules.sys.model.SysUser;
import com.ly.lyadmin.modules.sys.model.SysUserRole;
import com.ly.lyadmin.modules.sys.service.SysUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 栏目管理
 * @Author SLIGHTLEE
 * @Date 2019/11/25 3:12 下午
 * @Version V1.0
 */
@RestController
@RequestMapping("/bus/tc")
public class TColumnController extends AbstractController {

    @Autowired
    TColumnService tColumnService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    /**
     * @Description: 栏目菜单（要改动 ！！！）
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/11/27 4:53 下午
     */
    @RequestMapping(value = "/column",method = RequestMethod.POST)
    public Result columnById(){

        // 判断当前登录人所属平台
        SysUser user = getUser();
      /*  if(user.getUserId() == Constant.SUPER_ADMIN){
            // 所有平台权限
        }*/
        QueryWrapper<SysUserRole> qwrapper = new QueryWrapper<>();
        qwrapper.eq("user_id",user.getUserId());
        SysUserRole sysUserRole = sysUserRoleService.getOne(qwrapper);


        QueryWrapper<TColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pt_code",sysUserRole.getRoleId());

        queryWrapper.eq("status", Constant.STATUS_ISUSER);

        if(sysUserRole.getRoleId() == 0 || "0".equals(sysUserRole.getRoleId())){
            queryWrapper.last("limit 10");
        }
        /*else{
            queryWrapper.orderByAsc("order_num");
        }*/
        List<TColumn> list = tColumnService.list(queryWrapper);

        return Result.ok().put("columnList",list);

    }

    /**
     * @Description: 栏目列表显示 后台控制栏目是否显示到网站首页 导航
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/12/16 4:19 下午
     */

    @RequestMapping(value = "/columnList")
    public Result columnList(@RequestParam Integer page, @RequestParam Integer limit){

        // 根据当前登录账号 判断所属平台
        SysUser user = getUser();
        QueryWrapper<SysUserRole> qwrapper = new QueryWrapper<>();
        qwrapper.eq("user_id",user.getUserId());
        SysUserRole sysUserRole = sysUserRoleService.getOne(qwrapper);

        Result result = tColumnService.columnList(page, limit, sysUserRole.getRoleId());

        return result;
    }


    /**
     * @Description: 修改状态 显示
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/12/17 2:36 下午
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result update(@RequestBody TColumn tColumn){

        tColumnService.updateById(tColumn);

        return Result.ok();
    }
}
