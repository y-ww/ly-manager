package com.ly.lyadmin.modules.bus.controller;

import com.alibaba.fastjson.JSON;
import com.baidu.ueditor.ActionEnter;
import com.ly.lyadmin.modules.bus.model.Ueditor;
import com.ly.lyadmin.modules.bus.utils.PublicMsg;
import com.ly.lyadmin.modules.bus.utils.UploadUeditor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

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


    /*

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile) {
        System.out.println("文件上传： ："+upfile);
        Ueditor ueditor = new Ueditor();
        return ueditor;
    }*/

    @RequestMapping(value="/ueditorUpload")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public String imgUpload(MultipartFile upfile,HttpServletRequest request) {

        Ueditor ueditor = new Ueditor();
        UploadUeditor u=new UploadUeditor();
        String result="";
        String url="D:\\Program Files\\java\\apache-tomcat-8.5.47\\webapps\\vedio";

        if(upfile!=null){
             try {
                 result= u.uploadImg(upfile,request,url);
                 ueditor.setState("上传成功");
                 JSON.toJSONString(ueditor);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                ueditor.setState("出现异常");
                  JSON.toJSONString(ueditor);
            }
        }else{
            ueditor.setState("文件为空");
             JSON.toJSONString(ueditor);
        }
        return result;
    }

   /* public String uploadImg(MultipartFile file,
                            HttpServletRequest request) throws IOException {
        Ueditor ueditor = new Ueditor();
       // Users user = UserUtils.getUser(request) ;
       *//* String path = request.getSession().getServletContext()
                .getRealPath("ueditor/jsp/upload/image");*//*
        //D:\Program Files\java\apache-tomcat-8.5.47\webapps\vedio
       String  path="D:\\Program Files\\java\\apache-tomcat-8.5.47\\webapps\\vedio";
        System.out.println("上传地址：：："+path);
        String ct = file.getContentType() ;
        String fileType = "";
        if (ct.indexOf("/")>0) {
            fileType = ct.substring(ct.indexOf("/")+1);
        }
        String fileName = UUID.randomUUID() + "." + fileType;
        File targetFile = new File(path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        File targetFile2 = new File(path+"/"+fileName);
        if (!targetFile2.exists()) {
            targetFile2.createNewFile();
        }
        // 保存
        try {
            file.transferTo(targetFile2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ueditor.setState("SUCCESS");
        ueditor.setTitle(fileName);
        ueditor.setOriginal(fileName);
        ueditor.setUrl("http://127.0.0.1:8080/vedio/"+fileName);
        System.out.println("json：：：：格式："+ JSON.toJSONString(ueditor));
        return JSON.toJSONString(ueditor);
    }

*/




}
