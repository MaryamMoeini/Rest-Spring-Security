package com.RestProvider.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String USER = "USER";
	private static final String ADMIN = "ADMIN";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests().antMatchers("/meter/**")
		 .hasRole(USER)
		 .and()
		 .formLogin();
		 
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth
		 .inMemoryAuthentication().withUser("user").password("password").roles(USER)
		 .and().withUser("admin").password("password").roles(USER,ADMIN);
		 }
	}
