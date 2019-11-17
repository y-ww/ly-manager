package com.ly.lyadmin.modules.bus.controller;

import com.baidu.ueditor.ActionEnter;
import com.ly.lyadmin.modules.bus.model.Ueditor;
import com.ly.lyadmin.modules.bus.utils.PublicMsg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @Description: TODO
 * @Author SLIGHTLEE
 * @Date 2019/11/16 6:42 下午
 * @Version V1.0
 */
@PropertySource({"classpath:conf/resource-path.properties"})
@Controller
@RequestMapping("/ueditor")
public class UeditorController {


    @RequestMapping(value="/ueditorUpload")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile) {
        Ueditor ueditor = new Ueditor();
        return ueditor;
    }


}
