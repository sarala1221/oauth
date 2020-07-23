package com.api.gateway.cfg;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

	private static final String KEY_STORE_FILE = "/auth-jwt.jks";
	private static final String KEY_STORE_PASSWORD = "demo-pass";
	private static final String KEY_ALIAS = "demo-oauth-jwt";
	private static final String JWK_KID = "auth-key-id";
	private PasswordEncoder bCryptPasswordEncoder;
	private AuthenticationManager authenticationManager;
	private DataSource dataSource;

	AuthorizationServer(PasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager,
			DataSource dataSource) {
		this.authenticationManager = authenticationManager;
		this.dataSource = dataSource;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(bCryptPasswordEncoder).tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()").passwordEncoder(bCryptPasswordEncoder)
				.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer.jdbc(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore(accessTokenConverter()))
				.accessTokenConverter(accessTokenConverter());
	}

	@Bean
	public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
		return new JwtTokenStore(jwtAccessTokenConverter);
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		Map<String, String> customHeaders = Collections.singletonMap("kid", JWK_KID);
		return new JwtCustomHeadersAccessTokenConverter(customHeaders, keyPair());
	}

	@Bean
	public KeyPair keyPair() {
		ClassPathResource ksFile = new ClassPathResource(KEY_STORE_FILE);
		KeyStoreKeyFactory ksFactory = new KeyStoreKeyFactory(ksFile, KEY_STORE_PASSWORD.toCharArray());
		return ksFactory.getKeyPair(KEY_ALIAS);
	}

	@Bean
	public JWKSet jwkSet() {
		RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic()).keyUse(KeyUse.SIGNATURE)
				.algorithm(JWSAlgorithm.RS512).keyID(JWK_KID);
		return new JWKSet(builder.build());
	}

}
