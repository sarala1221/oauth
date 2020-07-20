package com.api.gateway.cfg;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

	private PasswordEncoder bCryptPasswordEncoder;
	private JwtTokenStore jwtTokenStore;
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	private AuthenticationManager authenticationManager;
	private DataSource dataSource;

	AuthorizationServer(PasswordEncoder bCryptPasswordEncoder, JwtTokenStore jwtTokenStore,
			JwtAccessTokenConverter jwtAccessTokenConverter, AuthenticationManager authenticationManager,
			DataSource dataSource) {
		this.authenticationManager = authenticationManager;
		this.dataSource = dataSource;
		this.jwtAccessTokenConverter = jwtAccessTokenConverter;
		this.jwtTokenStore = jwtTokenStore;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(bCryptPasswordEncoder).tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer.jdbc(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(jwtTokenStore)
				.accessTokenConverter(jwtAccessTokenConverter);
	}

}
