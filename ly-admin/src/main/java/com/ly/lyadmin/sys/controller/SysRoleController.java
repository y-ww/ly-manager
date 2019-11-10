package com.ly.lyadmin.sys.controller;

import com.ly.lyadmin.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
