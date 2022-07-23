package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityExpressionHandler expressionHandler() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
        DefaultWebSecurityExpressionHandler handler =
                new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .mvcMatchers("/", "/info", "/account/**", "/signup").permitAll() // 모두 허용
                    .mvcMatchers("/admin").hasRole("ADMIN") // ADMIN 관한이 있어야함
                    .mvcMatchers("/user").hasRole("USER")
                .anyRequest().authenticated() // 인증만되면 모두 접근 가능
                .expressionHandler(expressionHandler());

        http.httpBasic();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.formLogin()
                .loginPage("/login")
                .permitAll();

        http.sessionManagement()
                .sessionFixation()
                        .changeSessionId()
                .maximumSessions(1)
                        .maxSessionsPreventsLogin(true); // 추가 로그인을 막을지 여부

        // TODO ExceptionTranslationFilter -> FilterSecurityInterceptor (AccessDecisionManager, AffirmativeBase)
        // AuthenticationException -> AuthenticationEntryPoint : 인증이 가능한 페이지로 보냄
        // AccessDeniedException -> AccessDeniedHandler : 403 error 보여줌
        http.exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        String username = principal.getUsername();
                        System.out.println(username + " is denied to access " + request.getRequestURI());
                        response.sendRedirect("/access-denied");
                    }
                });

        // SecurityContext를 자식 쓰레드에도 공유하는 전략.
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

}
