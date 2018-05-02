package org.starlightfinancial.wechatweb.security.auth.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.starlightfinancial.wechatweb.security.auth.userdetails.CustomByOpenIdUserDetailServiceImpl;
import org.starlightfinancial.wechatweb.security.auth.token.OpenIdLoginAuthenticationToken;

/**
 * 微信openId登录的认证提供者
 *
 * @author senlin.deng
 */
public class OpenIdLoginAuthenticationProvider implements AuthenticationProvider {


    private CustomByOpenIdUserDetailServiceImpl customByOpenIdUserDetailServiceImpl;

    public CustomByOpenIdUserDetailServiceImpl getCustomByOpenIdUserDetailServiceImpl() {
        return customByOpenIdUserDetailServiceImpl;
    }

    public void setCustomByOpenIdUserDetailServiceImpl(CustomByOpenIdUserDetailServiceImpl customByOpenIdUserDetailServiceImpl) {
        this.customByOpenIdUserDetailServiceImpl = customByOpenIdUserDetailServiceImpl;
    }

    /**
     * 认证
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取过滤器封装的token信息
        OpenIdLoginAuthenticationToken authenticationToken = (OpenIdLoginAuthenticationToken) authentication;
        //获取用户信息（数据库认证）
        UserDetails userDetails = customByOpenIdUserDetailServiceImpl.loadUserByUsername((String) authenticationToken.getPrincipal());

        OpenIdLoginAuthenticationToken authenticationResult = new OpenIdLoginAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }


    /**
     * 根据token类型，来判断使用哪个Provider
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdLoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
