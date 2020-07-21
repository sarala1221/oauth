package com.api.gateway.cfg;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import net.minidev.json.JSONObject;

public class JwtCustomHeadersAccessTokenConverter extends JwtAccessTokenConverter {
	private Map<String, String> customHeaders = new HashMap<>();
	final RsaSigner signer;

	public JwtCustomHeadersAccessTokenConverter(Map<String, String> customHeaders, KeyPair keyPair) {
		super();
		super.setKeyPair(keyPair);
		this.signer = new RsaSigner((RSAPrivateKey) keyPair.getPrivate());
		this.customHeaders = customHeaders;
	}

	@Override
	protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		JSONObject content;
		try {

			content = new JSONObject(getAccessTokenConverter().convertAccessToken(accessToken, authentication));
			content.put("sub", content.get("user_name"));
			content.remove("user_name");
		} catch (Exception ex) {
			throw new IllegalStateException("Cannot convert access token to JSON", ex);
		}
		String token = JwtHelper.encode(content.toString(), this.signer, this.customHeaders).getEncoded();
		return token;
	}
}
