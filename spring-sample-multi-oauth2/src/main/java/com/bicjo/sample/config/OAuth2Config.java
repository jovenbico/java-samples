package com.bicjo.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()//
				.withClient("my_foo")//
				.authorizedGrantTypes("implicit", "password")//
				.scopes("read", "write")
				.redirectUris("http://localhost:8000/hello/world", "http://login-foo.example.com/hello/world")//
				.resourceIds("oauth2/foo")//
				.autoApprove(true)//
				.and()//
				.withClient("my_bar")//
				.authorizedGrantTypes("implicit", "password")//
				.scopes("read", "write")//
				.redirectUris("http://localhost:8000/bar/hello/world", "http://login-bar.example.com/bar/hello/world")//
				.resourceIds("oauth2/bar")//
				.autoApprove(true)//
		;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)//
				.tokenStore(tokenStore())//
		;
	}

	private TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

}
