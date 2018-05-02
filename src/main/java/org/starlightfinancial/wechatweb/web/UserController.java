package org.starlightfinancial.wechatweb.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.service.UserService;
import org.starlightfinancial.wechatweb.utils.EncryptHelper;
import org.starlightfinancial.wechatweb.utils.Util;
import org.starlightfinancial.wechatweb.utils.WebResultModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author senlin.deng
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

//    @RequestMapping("/login.do")
//    public String login(String username, String password, HttpSession session, String openId) {
//        User user = userService.findUser(username, password);
//        if (user != null && user.getId() > 0) {
//            user.setPassword(null);
//            session.setAttribute("user", user);
//            return "account-center";
//        }
//        session.setAttribute("msg", "登录失败,用户名或密码错误/账户不可用");
//        return "login";
//    }

    @RequestMapping("/openIdLogin.do")
    public String openIdLogin(HttpSession session, String openId) {
        User user = null;
        //根据微信openId登录
        if (StringUtils.isNotEmpty(openId)) {
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
     * @param mobile   手机号
     * @param password 密码
     * @param openId   微信openId
     * @param session  会话session
     * @return 返回视图名称
     */
    @RequestMapping("/register.do")
    @ResponseBody
    public WebResultModel register(String mobile, String password, String openId, HttpSession session) {
        WebResultModel resultModel = null;
        //避免直接被请求到进行了注册
        Boolean isSendSms = (Boolean) session.getAttribute("isSendSms");
        //避免重复发起请求进行了注册
        session.setAttribute("isSendSms", false);
        if (isSendSms != null && isSendSms && StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(password)) {
            User user = new User();
            user.setMobile(mobile);
            user.setPassword(EncryptHelper.Instance.getEncString(password));
            user.setRegisterTime(new Date());
            user.setIsDelete("0");
            user.setOpenId((String) session.getAttribute("openId"));
            userService.saveOrUpdateUser(user);
            resultModel = WebResultModel.success();
        } else {
            resultModel = WebResultModel.fail("请不要刷新本页面或重复提交表单！");
        }
        return resultModel;
    }

    /**
     * 验证手机号码是否可用
     *
     * @param mobile
     * @return
     */
    @RequestMapping("/isValid.do")
    @ResponseBody
    public WebResultModel isValid(String mobile) {
        WebResultModel resultModel = null;
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (mobile.length() != 11) {
            resultModel = WebResultModel.fail("手机号应为11位数");
            return resultModel;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(mobile);
            boolean isMatch = m.matches();
            if (!isMatch) {
                resultModel = WebResultModel.fail("请填入正确格式的手机号");
                return resultModel;
            } else {
                User user = userService.findByMobile(mobile);
                if (user != null) {
                    resultModel = WebResultModel.fail("此手机号已注册,请重新输入",1);
                } else {
                    resultModel = WebResultModel.success();
                }
                return resultModel;
            }
        }

    }

    /**
     * 注销登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User currentUser = Util.getCurrentUser();
            currentUser.setIsLogout("1");
            userService.saveOrUpdateUser(currentUser);
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";

    }

    /**
     * 更新账户的客户名称和身份证号
     *
     * @param username      客户名称
     * @param certificateNo 身份证号
     * @return 返回视图名称
     */
    @RequestMapping("/updateUser.do")
    @ResponseBody
    public WebResultModel updateUser(String username, String certificateNo) {
        WebResultModel resultModel = null;
        String certificateNoRegex = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";
        Pattern p = Pattern.compile(certificateNoRegex);
        Matcher m = p.matcher(certificateNo);
        boolean isMatch = m.matches();
        if (!isMatch) {
            resultModel = WebResultModel.fail("身份证格式有误,请重新填写");
        } else {
            User user = Util.getCurrentUser();
            if (user != null) {
                user.setUsername(username.trim());
                user.setCertificateNo(certificateNo.trim().toLowerCase());
                userService.saveOrUpdateUser(user);
            }
            resultModel = WebResultModel.success();
        }
        return resultModel;
    }



    /**
     * 重置密码
     *
     * @param mobile   手机号
     * @param password 密码
     * @param session  会话session
     * @return 返回视图名称
     */
    @RequestMapping("/resetPassword.do")
    @ResponseBody
    public WebResultModel resetPassword(String mobile, String password, HttpSession session) {
        WebResultModel resultModel = null;
        //避免直接被请求到进行了注册
        Boolean isSendSms = (Boolean) session.getAttribute("isSendSms");
        //避免重复发起请求进行了注册
        session.setAttribute("isSendSms", false);
        if (isSendSms != null && isSendSms && StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(password)) {
            User user = userService.findByMobile(mobile);
            user.setPassword(EncryptHelper.Instance.getEncString(password));
            userService.saveOrUpdateUser(user);
            resultModel = WebResultModel.success();
        } else {
            resultModel = WebResultModel.fail("请不要刷新本页面或重复提交表单！");
        }
        return resultModel;
    }



}
