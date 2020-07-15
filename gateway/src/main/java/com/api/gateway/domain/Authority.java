package com.api.gateway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "granted_authorities_list")
public class Authority implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9129807120209849738L;
	@Id
	@Column(name = "auth_id")
	private String id;
	@Column(name = "authority")
	private String authority;
	@ManyToOne
	private UserEntity entity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public UserEntity getEntity() {
		return entity;
	}

	public void setEntity(UserEntity entity) {
		this.entity = entity;
	}

}
