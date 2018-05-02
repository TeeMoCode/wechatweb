package org.starlightfinancial.wechatweb.security.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.starlightfinancial.wechatweb.security.auth.filter.OpenIdLoginAuthenticationFilter;
import org.starlightfinancial.wechatweb.security.auth.handler.CustomAuthenticationFailureHandler;
import org.starlightfinancial.wechatweb.security.auth.handler.CustomAuthenticationSuccessHandler;
import org.starlightfinancial.wechatweb.security.auth.provider.OpenIdLoginAuthenticationProvider;
import org.starlightfinancial.wechatweb.security.auth.userdetails.CustomByOpenIdUserDetailServiceImpl;

/**
 * @author senlin.deng
 */
@Configuration
public class OpenIdLoginAuthenticationSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>  {



    private String defaultOpenIdLoginUrl ="/login/wechat";
    private String defaultOpenIdLoginParameter="code";
    private String defaultOpenIdLoginHttpMethod ="GET";

    @Autowired
    private CustomByOpenIdUserDetailServiceImpl customByOpenIdUserDetailServiceImpl;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        OpenIdLoginAuthenticationFilter openIdLoginAuthenticationFilter = new OpenIdLoginAuthenticationFilter(defaultOpenIdLoginUrl, defaultOpenIdLoginParameter, defaultOpenIdLoginHttpMethod);
        openIdLoginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        openIdLoginAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        openIdLoginAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        OpenIdLoginAuthenticationProvider openIdLoginAuthenticationProvider = new OpenIdLoginAuthenticationProvider();
        openIdLoginAuthenticationProvider.setCustomByOpenIdUserDetailServiceImpl(customByOpenIdUserDetailServiceImpl);

        http.authenticationProvider(openIdLoginAuthenticationProvider)
                .addFilterAfter(openIdLoginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }


}
