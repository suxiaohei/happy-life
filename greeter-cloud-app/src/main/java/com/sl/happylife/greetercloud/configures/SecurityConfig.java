package com.sl.happylife.greetercloud.configures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author suxin
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**", "/css/**", "/webjars/**","/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                //登录, 并制定登录失败后页面
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .and()
                //默认 登出 /logout, 并指定登出后的操作.
                .logout().logoutSuccessUrl("/login");
    }
    // @formatter:on

    // @formatter:off
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("ROLE");
    }
    // @formatter:on
}
