package org.starlightfinancial.wechatweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers( "/login.do","/css/*", "/img/**", "/font/*", "/js/*").permitAll()
                .and().formLogin().loginPage("/login").loginProcessingUrl("/login.do").failureUrl("/login?error").permitAll().and()
                .logout().permitAll();
    }




    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

         auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");//创建一个默认的用户存储在内存中
    }
}
