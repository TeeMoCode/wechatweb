package org.starlightfinancial.wechatweb.security.auth.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.starlightfinancial.wechatweb.security.auth.lang.GetOpenIdUtil;
import org.starlightfinancial.wechatweb.security.auth.token.OpenIdLoginAuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信openId登录过滤器
 *
 * @author senlin.deng
 */
public class OpenIdLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private String openIdParameterName;


    public OpenIdLoginAuthenticationFilter(String openIdLoginUrl, String openIdParameterName,
                                           String httpMethod) {
        super(new AntPathRequestMatcher(openIdLoginUrl, httpMethod));
        this.openIdParameterName = openIdParameterName;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //获取微信服务器返回的code
        String code = getCode(request);
        //通过appid,secret和code获取openId
        String openId = GetOpenIdUtil.getOpenId(code);

        //将openId保存在session中
        request.getSession().setAttribute("openId", openId);

        //assemble token
        OpenIdLoginAuthenticationToken authRequest = new OpenIdLoginAuthenticationToken(openId);
//        1234567891 for test
//        OpenIdLoginAuthenticationToken authRequest = new OpenIdLoginAuthenticationToken("1234567891");

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 设置身份认证的详情信息
     */
    private void setDetails(HttpServletRequest request, OpenIdLoginAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 获取微信服务器返回的code
     */
    private String getCode(HttpServletRequest request) {
        return request.getParameter(openIdParameterName);
    }

}
