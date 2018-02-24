package org.starlightfinancial.wechatweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers( "/login.do","/register","/register-detail","/code/**",
                "/isValid","/css/*", "/img/**", "/font/*", "/js/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").loginProcessingUrl("/user/login.do").failureUrl("/login?error").permitAll().and()
                .logout().permitAll();
    }

}
