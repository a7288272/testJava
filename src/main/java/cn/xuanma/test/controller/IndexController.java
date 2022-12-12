package cn.xuanma.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:wangshu'an
 * @date:2022/12/5 16:18
 * @Description:
 */
@Controller
@RequestMapping("/index/")
public class IndexController {
    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
