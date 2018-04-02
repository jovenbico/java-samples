package com.bicjo.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class BarWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("barAuthenticationProvider")
	private AuthenticationProvider barAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()//
				.antMatcher("/bar/**")//
				.authorizeRequests()//
				.anyRequest().authenticated()//
				.and()//
				.formLogin()//
				.loginPage("/bar/login")//
				.loginProcessingUrl("/bar/login")//
				.permitAll()//
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth//
				.authenticationProvider(barAuthenticationProvider)//
		// .inMemoryAuthentication()//
		// .withUser("me").password("pass").roles("USER")//
		;
	}
}
