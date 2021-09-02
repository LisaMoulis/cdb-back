package dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authorities")
public class AuthorityDTO implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String authority;
	
	public AuthorityDTO(String string) {
		this.authority = string;
	}

	public AuthorityDTO()
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
