package api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDTO;
import mapper.UserDTOMapper;
import service.UserService;


@RestController
@RequestMapping("/service")
public class UserWebService {

	private UserService userService;
	private UserDTOMapper userMapper;
	
	@Autowired
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}
	
	@Autowired
	public void setUserService(UserDTOMapper userMapper)
	{
		this.userMapper = userMapper;
	}
	
	
	@RequestMapping(value = "/login", params = {"username"}, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getUserAuthority(@RequestParam("username") String username)
	{
		return "{ \"authority\":\"" + userService.getUser(username).getAuthority() + "\" }";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addUser(@RequestBody @Valid UserDTO user)
	{
		userService.registerUser(userMapper.mapToUser(user));
	}
}
