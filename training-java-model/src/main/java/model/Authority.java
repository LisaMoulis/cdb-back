package model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;
	
	public Authority(String string) {
		this.authority = string;
	}

	public Authority()
	{}

	@Override
	public String getAuthority() {
		return this.authority;
	}
	
	public void setAuthority(String id)
	{
		this.authority = id;
	}

}
