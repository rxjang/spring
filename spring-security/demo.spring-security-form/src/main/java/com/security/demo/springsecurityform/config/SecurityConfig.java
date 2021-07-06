package com.security.demo.springsecurityform.config;

import com.security.demo.springsecurityform.account.AccountService;
import com.security.demo.springsecurityform.common.LoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountService accountService;

//    public AccessDecisionManager accessDecisionManager() {
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
//
//        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
//        handler.setRoleHierarchy(roleHierarchy);
//
//        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
//        webExpressionVoter.setExpressionHandler(handler);
//        List<AccessDecisionVoter<? extends Object>> voters = Arrays.asList(webExpressionVoter);
//        return new AffirmativeBased(voters);
//    }

    public SecurityExpressionHandler expressionHandler() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);

        return handler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

//        web.ignoring().mvcMatchers("favicon.ico");리
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        // 동적으로 거르는 것은 추천하지 X => 루트 페이지는 모든 사용자함( 인증 O, 인증 X) 접속 가능 => 필터를 타야 함리
        // 정적인 resource 위주로 처리 => 예외적인 정적 자원만 http.authorizedRequest 에서 처리
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new LoggingFilter(), WebAsyncManagerIntegrationFilter.class);

        http.authorizeRequests()
                .mvcMatchers("/", "/signup", "/info", "/account/**").permitAll()
                .mvcMatchers("/admin").hasRole("ADMIN")
                // hasAuthority 사용시 ROLE_ 접두사 필요
                .mvcMatchers("/user").hasAuthority("ROLE_USER")
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().authenticated()
                .expressionHandler(expressionHandler());
//                .accessDecisionManager(accessDecisionManager());
        http.formLogin().loginPage("/login").permitAll();
        http.httpBasic();

        http.rememberMe()
                .userDetailsService(accountService)
                .key("remember-me-sample");

        http.logout().logoutSuccessUrl("/");

        http.anonymous().principal("anonymousUser");

        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)   // REST API 사용시 전략
                .maximumSessions(1)
                    .maxSessionsPreventsLogin(true);

        // TODO ExceptionTranslatorFilter -> FilterSecurityInterceptor (AccessDecisionManager, AffirmativeBased)
        // TODO AuthenticationException -> AuthenticationEntryPoint
        // TODO AccessDeniedException -> AccessDeniedHandler(403)

        http.exceptionHandling().
                accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                    // 별도의 클래스로 빼는 것이 좋음
                    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username = principal.getUsername();
                    System.out.println(username + " is denied to access "+ httpServletRequest.getRequestURI());
                    httpServletResponse.sendRedirect("/access-denied");
                });

        // 현재 쓰레드에서 생성 되는 하위 쓰레드로 security context 공유
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
