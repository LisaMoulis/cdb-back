package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import model.User;
import persistence.UserDAO;

@Component("userService")
@Scope("singleton")
public class UserService implements UserDetailsService {

	private UserDAO userDAO;
	
	@Autowired
	public UserService(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userDAO.getUser(username);
	}
	
	public void registerUser(User user)
	{
		userDAO.registerUser(user);
	}
	
	
}
