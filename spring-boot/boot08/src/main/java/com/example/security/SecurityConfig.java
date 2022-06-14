package com.example.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ExampleUserService userService;

    private final DataSource dataSource;

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
                .logout().logoutUrl("/logout").invalidateHttpSession(true)
            .and()
                .rememberMe().key("example").userDetailsService(userService)
                .tokenRepository(getJDBCRepository()).tokenValiditySeconds(60 * 60 * 24);

    }

//     @Autowired
//     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//         log.info("build Auth global........");
//
//         String query1 = "SELECT uid username, upw password, true enabled FROM tbl_members WHERE uid= ?";
//
//         String query2 = "SELECT member uid, role_name role FROM tbl_member_roles WHERE member = ?";
//
//         auth.jdbcAuthentication()
//                 .dataSource(dataSource)
//                 .usersByUsernameQuery(query1)
//                 .rolePrefix("ROLE_")
//                 .authoritiesByUsernameQuery(query2);
//
//     }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    private PersistentTokenRepository getJDBCRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        log.info("build Auth global.....");

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}
