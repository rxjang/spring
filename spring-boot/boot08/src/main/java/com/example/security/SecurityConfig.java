package com.example.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 웹 자원에 대한 보안 확인
     * authorizeRequests() -> 시큐리티 처리에 HttpServrletRequest를 이용한다는 것을 의미
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("security config.......");

        http.authorizeHttpRequests()
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
            .and()
                .formLogin().loginPage("/login")
            .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
            .and()
                .logout().logoutUrl("/logout").invalidateHttpSession(true);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        log.info("build Auth global...");

        auth.inMemoryAuthentication()
                .withUser("manager")
                .password("1111")
                .roles("MANAGER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };
    }

}
