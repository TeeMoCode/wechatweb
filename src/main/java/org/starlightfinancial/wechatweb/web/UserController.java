package org.starlightfinancial.wechatweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.service.UserService;
import org.starlightfinancial.wechatweb.utils.EncryptHelper;
import org.starlightfinancial.wechatweb.utils.WebResultModel;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public String login(String username, String password, HttpSession session, String openId) {
        User user = userService.findUser(username, password);
        if (user != null && user.getId() > 0) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "account-center";
        }
        session.setAttribute("msg", "登录失败,用户名或密码错误/账户不可用");
        return "login";
    }

    @RequestMapping("/openIdLogin.do")
    public String openIdLogin(HttpSession session, String openId) {
        User user = null;
        //根据微信openId登录
        if (!StringUtils.isEmpty(openId)) {
            user = userService.findByOpenId(openId);
        }
        if (user != null && user.getId() > 0) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "account-center";
        }
        session.setAttribute("msg", "登录失败,用户名或密码错误/账户不可用");
        return "login";
    }


    /**
     * 用户注册
     *
     * @param mobile
     * @param password
     * @param openId
     * @param session
     * @return
     */
    @RequestMapping("/register")
    public String register(String mobile, String password, String openId, HttpSession session) {
        //避免直接被请求
        Boolean isSendSms = (Boolean) session.getAttribute("isSendSms");
        if (isSendSms && StringUtils.hasLength(mobile) && StringUtils.hasLength(password)) {
            User user = new User();
            user.setMobile(mobile);
            user.setPassword(EncryptHelper.Instance.getEncString(password).trim());
            user.setRegisterTime(new Date());
            user.setOpenId(openId);
            user.setIsDelete("0");
            userService.saveUser(user);
            return "account-center";
        } else {
            return "register";
        }
    }

    @RequestMapping("/isValid")
    @ResponseBody
    public WebResultModel isValid(String mobile) {
        WebResultModel resultModel = null;
        User user = userService.findByMobile(mobile);
        if (user != null) {
            resultModel = WebResultModel.fail("此手机号已注册,请重新输入");
        } else {
            resultModel = WebResultModel.ok();
        }
        return resultModel;

    }

}
