package com.ly.lyadmin.modules.bus.controller;

import com.ly.common.utils.Result;
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
public class ContentController {

    @RequestMapping("/save")
    public Result save(@RequestParam String contentTitle,@RequestParam String content,@RequestParam String imageUrl,@RequestParam String html){

        System.out.println("contentTitle"+contentTitle+"---" + content+"---" + html);

        return Result.ok();

    }
}
