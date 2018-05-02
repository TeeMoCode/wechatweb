package org.starlightfinancial.wechatweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.starlightfinancial.wechatweb.interceptor.CheckUserInfoInterceptor;

/**
 * @author senlin.deng
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/account-center").setViewName("account-center");
        registry.addViewController("/loan-history").setViewName("loan-history");
        registry.addViewController("/loan-detail").setViewName("loan-detail");
        registry.addViewController("/account-manager").setViewName("account-manager");
        registry.addViewController("/account-setting").setViewName("account-setting");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/register-detail").setViewName("register-detail");
        registry.addViewController("/").setViewName("account-center");
        registry.addViewController("/reset-password").setViewName("reset-password");
        registry.addViewController("/loan-application").setViewName("loan-application");
        registry.addViewController("/reset-password-detail").setViewName("reset-password-detail");
    }


    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckUserInfoInterceptor()).addPathPatterns("/loan/loan-history.do", "/loan-progress", "/loan-detail", "/loan-application");

    }

}
