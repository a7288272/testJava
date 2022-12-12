package cn.xuanma.test.controller;

import cn.xuanma.test.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:wangshu'an
 * @date:2022/12/5 11:40
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @RequestMapping("/login")
    public String login(String username,String password){
        try {
            userService.checkLogin(username,password);
            return "index";
        } catch (Exception e) {
            System.out.println("密码错误");
            return "login";
        }
    }
}
