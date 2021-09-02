package mapper;

import dto.PageListDTO;
import model.ComputerList;
import org.springframework.stereotype.Component;


@Component
public class PageListMapper {

	public ComputerList mapToComputerList(PageListDTO dto)
	{
		ComputerList list = new ComputerList(dto.getPage(),dto.getSize());
		
		return list;
	}
}
