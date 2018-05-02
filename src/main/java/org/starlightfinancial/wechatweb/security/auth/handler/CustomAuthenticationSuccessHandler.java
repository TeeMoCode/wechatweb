package org.starlightfinancial.wechatweb.security.auth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.starlightfinancial.wechatweb.domain.User;
import org.starlightfinancial.wechatweb.enums.ConstantsEnum;
import org.starlightfinancial.wechatweb.service.UserService;
import org.starlightfinancial.wechatweb.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 微信openId登录认证成功处理器
 *
 * @author senlin.deng
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class.getName());

    @Autowired
    private UserService userService;

    public CustomAuthenticationSuccessHandler() {
        logger.info("CustomAuthenticationSuccessHandler loading ...");
    }

    /**
     * 登录成功被调用
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        User currentUser = Util.getCurrentUser();
        logger.info("认证通过,"+currentUser.getMobile());
        currentUser.setLastLoginTime(new Date());
        //设置是否注销:0代表未注销,1代表注销
        currentUser.setIsLogout(ConstantsEnum.FAIL.getCode());
        userService.saveOrUpdateUser(currentUser);

    }
}
