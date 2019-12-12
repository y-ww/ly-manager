package com.ly.lyadmin.modules.sys.controller;

import com.ly.common.utils.IDUtils;
import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.sys.model.SysRole;
import com.ly.lyadmin.modules.sys.model.SysRoleMenu;
import com.ly.lyadmin.modules.sys.service.SysRoleMenuService;
import com.ly.lyadmin.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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

    @Autowired
    SysRoleMenuService sysRoleMenuService;


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

    /**
     * @Description:  添加角色信息
     * @Param:
     * @Return:
     * @Author: SLIGHTLEE
     * @Email: lmm_work@163.com
     * @Date: 2019/12/9 10:10 上午
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysRole sysRole){

        // 添加角色
        long id = IDUtils.genItemId();
        sysRole.setRoleId(id);
        sysRole.setCreateTime(new Date());
        sysRoleService.save(sysRole);

       // 获取 codes  值
        List<Long> codes = sysRole.getCodes();

        // 添加 角色菜单
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId(id);
        for (int i = 0; i < codes.size(); i++) {
            sysRoleMenu.setMenuId(codes.get(i));
            sysRoleMenuService.save(sysRoleMenu);
            System.out.println(sysRoleMenu);
        }

        return Result.ok();
    }
}
