package com.ly.lyadmin.modules.bus.utils;

import com.alibaba.fastjson.JSON;
import com.ly.lyadmin.modules.bus.model.Ueditor;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUeditor {

    public String uploadImg(MultipartFile file,
                            HttpServletRequest request,String url) throws IOException {
        Ueditor ueditor = new Ueditor();
        //D:\Program Files\java\apache-tomcat-8.5.47\webapps\vedio
        String  path=url;
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


}
