package com.ly.lyadmin.modules.bus.controller;

import com.ly.common.utils.Result;
import com.ly.lyadmin.util.FileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/13 10:47 下午
 * @Version V1.0
 */
@PropertySource({"classpath:conf/resource-path.properties"})
@RestController
@RequestMapping("/pic")
public class PictureController {

    /**
     *  取 配置文件里面的属性值
     */
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @Value("${IMAGE_UPLOAD_URL}")
    private String IMAGE_UPLOAD_URL;

    /**
     *  图片上传
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result upload(MultipartFile file) {

        String url;
        String path = FileUpload.upload(file, IMAGE_SERVER_URL, IMAGE_UPLOAD_URL);
        return Result.ok().put("url",path);
    }

}
