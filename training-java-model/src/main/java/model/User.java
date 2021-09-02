package model;

import java.util.ArrayList;

import javax.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import dto.AuthorityDTO;

public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String authority;
	private boolean enabled = true;
	private boolean accountExpired = false;
	private boolean locked = false;
	private boolean credentialExpired = false;
	
	public User(String name, String encode) {
		this.username = name;
		this.password = encode;
		this.authority = "USER";
		this.enabled = true;
		//this.authorities = new ArrayList<Authority>();
		//this.authorities.add(new Authority("USER"));
	}

	public User()
	{}
	
	public User(String username, String password, String authority) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}

	@Override
	public ArrayList<Authority> getAuthorities() {
		ArrayList<Authority> authorities = new ArrayList<Authority>();
		authorities.add(new Authority(this.authority));
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.credentialExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
