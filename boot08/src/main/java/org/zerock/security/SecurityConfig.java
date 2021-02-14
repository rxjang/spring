package org.zerock.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	ZerockUsersService zerockUserService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		log.info("Security config...........");
		
		http.authorizeRequests().antMatchers("/guest/**").permitAll();
		
		http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
		
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		
		http.formLogin().loginPage("/login");
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
		//세션 무효화 
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
//		http.userDetailsService(zerockUserService);
		
		http.rememberMe()
		.key("zerock")
		.userDetailsService(zerockUserService)
		.tokenRepository(getJDBCRepository())
		.tokenValiditySeconds(60*60*24);
		
		//초단위 ->  24시간 유지 
	}
	
	@Autowired
	private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		log.info("build Auth global.............");
		
//		String query1 = "SELECT uid username, CONCAT('{noop}', upw) password, true enabled FROM "
//				+ "tbl_members WHERE uid = ?";
//		
//		String query2 = "SELECT member uid, role_name role FROM tbl_member_roles WHERE member =?";
//		
//		auth.jdbcAuthentication()
//		.usersByUsernameQuery(query1)
//		.rolePrefix("ROLE_")
//		.authoritiesByUsernameQuery(query2);

	}
	
	private PersistentTokenRepository getJDBCRepository() {
		
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
		
	}
	

}
