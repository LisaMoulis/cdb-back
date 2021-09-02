package mapper;

import org.springframework.stereotype.Component;

import dto.UserDTO;
import model.User;

@Component
public class UserDTOMapper {

	public User mapToUser(UserDTO dto)
	{
		if (dto != null)
		{
			return new User(dto.getUsername(),dto.getPassword(),dto.getAuthorities().get(0).getAuthority());
		}
		return new User();
	}
	
	public UserDTO mapToDTO(User user)
	{
		if (user != null)
		{
			return new UserDTO(user.getUsername(),user.getPassword(),user.getAuthorities().get(0).getAuthority());
		}
		return new UserDTO();
	}
}
