package com.isaac.ch12.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth)  throws Exception {
        try {
            auth.inMemoryAuthentication().withUser("isaac").password("isaac").roles("ADMIN");
        } catch (Exception e) {
            logger.error("Could not configure authentication!", e);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//允许配置是否在身份验证时创建HTTP会话，RESTful-WS是无状态的所以不创建会话
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/rest/**").hasRole("ADMIN").anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
