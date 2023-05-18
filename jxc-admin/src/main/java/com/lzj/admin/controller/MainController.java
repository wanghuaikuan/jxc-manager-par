package com.lzj.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    /**
     * 系统登录页
     * @return
     */
    @RequestMapping("/index")

    public String index(){
        return "index";
    }
    /**
     * 系统主页面
     * @return
     */
    @RequestMapping("main")
    public String main(){
        return "main";
    }
    /**
     * 系统欢迎页
     * @return
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

//    /**
//     * 用户退出
//     * @return
//     */
//    @RequestMapping("signout")
//    public String sigcont(HttpSession session){
//        session.removeAttribute("user");
//        return"redirect:index";
//    }
}
