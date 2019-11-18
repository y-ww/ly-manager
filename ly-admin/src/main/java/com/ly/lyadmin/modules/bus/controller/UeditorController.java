package com.ly.lyadmin.modules.bus.controller;

import com.alibaba.fastjson.JSON;
import com.ly.lyadmin.modules.bus.model.Ueditor;
import com.ly.lyadmin.modules.bus.utils.ueditor.PublicMsg;
import com.ly.lyadmin.modules.bus.utils.ueditor.UploadUeditor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description: 百度编辑器
 * @Author SLIGHTLEE
 * @Date 2019/11/16 6:42 下午
 * @Version V1.0
 */
@PropertySource({"classpath:conf/resource-path.properties"})
@Controller
@RequestMapping("/ueditor")
public class UeditorController {

    /**
     *
     * UEDITOR_IMAGE_UPLOAD_PATH  ueditor 编辑器 图片上传路径
     * UEDITOR_IMAGE_REAL_PATH    ueditor 编辑器 图片上传访问路径
     * UEDITOR_VEDIO_UPLOAD_PATH  ueditor 编辑器 视频上传路径
     * UEDITOR_VEDIO_REAL_PATH    ueditor 编辑器 视频上传访问路径
     *
     */
    @Value("${UEDITOR_IMAGE_UPLOAD_PATH}")
    private String UEDITOR_IMAGE_UPLOAD_PATH;

    @Value("${UEDITOR_IMAGE_REAL_PATH}")
    private String UEDITOR_IMAGE_REAL_PATH;

    @Value("${UEDITOR_VEDIO_UPLOAD_PATH}")
    private String UEDITOR_VEDIO_UPLOAD_PATH;

    @Value("${UEDITOR_VEDIO_REAL_PATH}")
    private String UEDITOR_VEDIO_REAL_PATH;


    /*

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile) {
        System.out.println("文件上传： ："+upfile);
        Ueditor ueditor = new Ueditor();
        return ueditor;
    }*/

    /**
     * ueditor.config.js  serverUrl 路径
     * @param request
     * @return
     */
    @RequestMapping(value="/ueditorUpload")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }

    /**
     * 百度编辑器图片上传
     * @param upfile
     * @param request
     * @return
     */
    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public String imgUpload(MultipartFile upfile,HttpServletRequest request) {

        Ueditor ueditor = new Ueditor();

        String result="";
        //  String url="D:\\Program Files\\java\\apache-tomcat-8.5.47\\webapps\\vedio";
        String uploadUrl = UEDITOR_IMAGE_UPLOAD_PATH;
        String showUrl = UEDITOR_IMAGE_REAL_PATH;


        if(upfile!=null){
            try {
                result= UploadUeditor.uploadImg(upfile,request,uploadUrl,showUrl);
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


    /**
     * 百度编辑器图片上传
     * @param upfile
     * @param request
     * @return
     */
    @RequestMapping(value="/vedioUpload")
    @ResponseBody
    public String vedioUpload(MultipartFile upfile,HttpServletRequest request) {

        Ueditor ueditor = new Ueditor();

        String result="";
        //  String url="D:\\Program Files\\java\\apache-tomcat-8.5.47\\webapps\\vedio";
        String uploadUrl = UEDITOR_VEDIO_UPLOAD_PATH;
        String showUrl = UEDITOR_VEDIO_REAL_PATH;

        if(upfile!=null){
            try {
                result= UploadUeditor.uploadImg(upfile,request,uploadUrl,showUrl);
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


}
