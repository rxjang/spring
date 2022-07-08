package com.example.config;

import com.example.account.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .mvcMatchers("/", "/info", "/account/**").permitAll() // 모두 허용
                    .mvcMatchers("/admin").hasRole("ADMIN") // ADMIN 관한이 있어야함
                    .anyRequest().authenticated() // 인증만되면 모두 접근 가능
                    .and()
                .formLogin()
                    .and()
                .httpBasic()
        ;
    }

}
