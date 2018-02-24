package org.starlightfinancial.wechatweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/account-center").setViewName("account-center");
        registry.addViewController("/loan-history").setViewName("loan-history");
        registry.addViewController("/loan-application").setViewName("loan-application");
        registry.addViewController("/loan-detail").setViewName("loan-detail");
        registry.addViewController("/account-manager").setViewName("account-manager");
        registry.addViewController("/account-setting").setViewName("account-setting");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/register-detail").setViewName("register-detail");
    }

}
