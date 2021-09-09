package api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.UserService;


@RestController
@RequestMapping("/service/login")
public class UserWebService {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
	@RequestMapping(params = {"username"}, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Collection<? extends GrantedAuthority> getUserAuthorities(@RequestParam("username") String username)
	{
		return userService.loadUserByUsername(username).getAuthorities();
	}
}
