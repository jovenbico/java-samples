package com.bicjo.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("fooAuthenticationProvider")
	private AuthenticationProvider fooAuthenticationProvider;

	@Autowired
	@Qualifier("barAuthenticationProvider")
	private AuthenticationProvider barAuthenticationProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()//
				.authorizeRequests()//
				.antMatchers("/login", "/*/login").permitAll()//
				.anyRequest().authenticated()//
				.and()//
				.formLogin()//
				.loginPage("/login")//
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth//
				.authenticationProvider(fooAuthenticationProvider)//
				.authenticationProvider(barAuthenticationProvider)
		// .inMemoryAuthentication()//
		// .withUser("me").password("pass").roles("USER")//
		;
	}

}
