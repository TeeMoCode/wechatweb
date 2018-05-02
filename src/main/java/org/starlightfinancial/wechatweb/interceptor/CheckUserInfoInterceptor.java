package org.starlightfinancial.wechatweb.interceptor;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.utils.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检验用户信息中的客户名称和身份证号是否完整
 * @author senlin.deng
 */
public class CheckUserInfoInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = Util.getCurrentUser();
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getCertificateNo())) {
            response.sendRedirect("/account-setting");
            return false;
        }
        return true;
    }


}
