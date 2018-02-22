package org.starlightfinancial.wechatweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login.do")
    public String login(String username, String password, HttpSession session) {
        // TODO: 2018/2/5 登录
        return "account-center";
    }
}
