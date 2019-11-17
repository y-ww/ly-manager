package com.ly.lyadmin.modules.bus.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /**
     *  取 配置文件里面的属性值
     */
    @Value("${IMAGE_SERVER_URL}")
    private String SERVER_NAME_URL;

    @RequestMapping(value="/ueditorUpload",method= RequestMethod.GET)
    public void ueditorUpload(HttpServletRequest request, HttpServletResponse response){

        try {
            request.setCharacterEncoding( "utf-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Type" , "text/html");
      // String rootPath = request.getSession().getServletContext().getRealPath("/");

        String rootPath = request.getSession().getServletContext().getRealPath("/");


        String exec = new ActionEnter(request, rootPath).exec();
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
