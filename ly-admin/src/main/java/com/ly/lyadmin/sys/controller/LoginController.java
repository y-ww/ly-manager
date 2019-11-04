package com.ly.lyadmin.sys.controller;

import com.ly.common.utils.Result;
import com.ly.lyadmin.shiro.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*import com.google.code.kaptcha.Constants;*/



/**
 * @Description: 登陆
 *
 * @Author: SLIGHTLEE
 * @Date: 2019/10/25 12:28 上午
 *
 */
@Controller
@RequestMapping("/sys")
public class LoginController {


    /**
     *  登录
     * @param username
     * @param password
     * @param captcha
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(String username, String password, String captcha){

//        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
//        if(!captcha.equalsIgnoreCase(kaptcha)){
//            return Result.error("验证码不正确");
//        }

        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
        }catch (UnknownAccountException e) {
            return Result.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return Result.error("账号或密码不正确");
        }catch (LockedAccountException e) {
            return Result.error("账号已被锁定,请联系管理员");
        }catch (AuthenticationException e) {
            return Result.error("账户验证失败");
        }

        return Result.ok();
    }

    /**
     *  退出
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        ShiroUtils.logout();
        return "redirect:login.html";
    }
}
