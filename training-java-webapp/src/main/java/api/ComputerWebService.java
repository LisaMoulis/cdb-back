package api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import dto.ComputerDTO;
import mapper.ComputerDTOMapper;
import model.ComputerList;
import service.ComputerService;
import service.PageService;

@RestController
@RequestMapping("/service/computers")
public class ComputerWebService {

	private ComputerService computerService;
	private ComputerDTOMapper computerMapper;
	private PageService pageService;
	
	@Autowired
	public void setComputerService(ComputerService computerService)
	{
		this.computerService = computerService;
	}
	
	@Autowired
	public void setComputerDTOMapper(ComputerDTOMapper computerDTOMapper)
	{
		this.computerMapper = computerDTOMapper;
	}
	
	@Autowired
	public void setPageService(PageService pageService)
	{
		this.pageService = pageService;
	}
	
	@RequestMapping(params = {"id"}, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ComputerDTO getComputer(@RequestParam("id") int id)
	{
		return computerMapper.mapToDTO(computerService.getComputer(id));
	}
	
	@RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ComputerDTO getComputer(@RequestParam("name") String name)
	{
		return computerMapper.mapToDTO(computerService.getComputer(name));
	}
	

	@RequestMapping(params = {"page","size"}, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ComputerDTO> getAllComputers(@RequestParam("page") int page, @RequestParam("size") int size)
	{
		ComputerList list = new ComputerList();
		list.setPage(page);
		list.setSize(size);
		return computerMapper.mapToDTOList(pageService.getPage(list,"","computer.id","asc"));
	}
		

	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addComputer(@RequestBody @Valid ComputerDTO computer)
	{
		computerService.createComputer(computerMapper.mapToComputer(computer));
	}
	
	@RequestMapping(value= "/update",method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void updateComputer(@RequestBody @Valid ComputerDTO computer)
	{
		System.out.println(computer);
		computerService.updateComputer(computerMapper.mapToComputer(computer));
	}
	
	@RequestMapping(params = {"id"}, method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteComputer(@RequestParam("id") int id)
	{
		computerService.removeComputer(id);
	}
	
	@RequestMapping(params = {"name"}, method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void deleteComputer(@RequestParam("name") String name)
	{
		computerService.removeComputer(name);
	}
}
