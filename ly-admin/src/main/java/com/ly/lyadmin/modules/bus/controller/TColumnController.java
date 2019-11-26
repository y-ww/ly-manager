package com.ly.lyadmin.modules.bus.controller;

import com.ly.common.utils.Result;
import com.ly.lyadmin.modules.bus.model.TColumn;
import com.ly.lyadmin.modules.bus.service.TColumnService;
import com.ly.lyadmin.modules.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     *  栏目菜单
     */
    /*@RequestMapping("/tcolumnList")
    public Result navigation(){
        List<TColumn> menuList = tColumnService.getUserTColumnList(getUserId());
        return Result.ok().put("menuList",menuList);
    }*/

}
