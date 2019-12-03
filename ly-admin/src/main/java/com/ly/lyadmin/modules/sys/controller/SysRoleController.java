package com.ly.lyadmin.modules.sys.controller;

import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: MING
 * @Eamil lmm_work@163.com
 * @Date: 2018/12/11 14:11
 */
@RestController
@RequestMapping("sys/role")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;

    /**
     * @Description: 角色列表
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/12/3 4:46 下午
     */
    @RequestMapping("/rolelist")
    public Result list(@RequestParam Integer page, @RequestParam Integer limit,
                       @RequestParam(required = false) String searchKey,
                       @RequestParam(required = false) String searchValue){
        Result r = sysRoleService.getRoleList(page, limit, searchKey, searchValue);
        return r;
    }
}
