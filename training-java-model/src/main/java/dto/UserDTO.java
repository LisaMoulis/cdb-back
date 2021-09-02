package dto;

import java.util.ArrayList;

import javax.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class UserDTO implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String username;
	@Column
	private String password;
	@Column
	private String authority;
	@Column
	private boolean enabled;
	@Transient
	private boolean accountExpired = false;
	@Transient
	private boolean locked = false;
	@Transient
	private boolean credentialExpired = false;
	
	public UserDTO(String name, String encode) {
		this.username = name;
		this.password = encode;
		this.authority = "USER";
		this.enabled = true;
		//this.authorities = new ArrayList<Authority>();
		//this.authorities.add(new Authority("USER"));
	}

	public UserDTO()
	{}
	
	public UserDTO(String username, String password, String authority) {
		this.username = username;
		this.password = password;
		this.authority = authority;
	}

	@Override
	public ArrayList<AuthorityDTO> getAuthorities() {
		ArrayList<AuthorityDTO> authorities = new ArrayList<AuthorityDTO>();
		authorities.add(new AuthorityDTO(this.authority));
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
