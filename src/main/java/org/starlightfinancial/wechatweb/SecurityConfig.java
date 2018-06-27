package org.starlightfinancial.wechatweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.starlightfinancial.wechatweb.security.CustomByMobileUserDetailServiceImpl;
import org.starlightfinancial.wechatweb.security.encoder.CustomPasswordEncoder;
import org.starlightfinancial.wechatweb.security.auth.config.OpenIdLoginAuthenticationSecurityConfig;
import org.starlightfinancial.wechatweb.security.auth.handler.CustomAuthenticationSuccessHandler;

/**
 * @author senlin.deng
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OpenIdLoginAuthenticationSecurityConfig openIdLoginAuthenticationSecurityConfig;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    UserDetailsService getSystemUserDetailService() {
        return new CustomByMobileUserDetailServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(getSystemUserDetailService());
        authenticationProvider.setPasswordEncoder(new CustomPasswordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getSystemUserDetailService());
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers( "/user/login.do","/register","/register-detail","/code/**","/user/register.do",
                "/user/isValid.do","/reset-password","/reset-password-detail","/user/resetPassword.do","/css/*", "/img/**", "/font/*", "/js/*").permitAll()
                .anyRequest().authenticated().and().apply(openIdLoginAuthenticationSecurityConfig)
                .and().formLogin().loginPage("/login").successHandler(customAuthenticationSuccessHandler).failureUrl("/login?error=1").permitAll().and()
                .logout().permitAll();

    }

}
