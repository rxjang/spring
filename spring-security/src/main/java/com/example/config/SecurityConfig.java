package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .mvcMatchers("/", "/info").permitAll() // 모두 허용
                    .mvcMatchers("/admin").hasRole("ADMIN") // ADMIN 관한이 있어야함
                    .anyRequest().authenticated() // 인증만되면 모두 접근 가능
                    .and()
                .formLogin()
                    .and()
                .httpBasic()
        ;
    }

    /**
     * 유저정보를 임의대로 설정 가능
     * {noop} spring security default password encoder -> 암호화를 하지 않음
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test").password("{noop}1234").roles("USER")
                .and()
                .withUser("admin").password("{noop}1234!").roles("ADMIN");
    }
}
