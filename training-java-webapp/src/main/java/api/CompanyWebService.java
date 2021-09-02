package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.CompanyDTO;
import mapper.CompanyDTOMapper;
import service.CompanyService;


@RestController
@RequestMapping("/service/companies")
public class CompanyWebService {

	private CompanyService companyService;
	private CompanyDTOMapper companyMapper;
	
	@Autowired
	public void setCompanyService(CompanyService companyService)
	{
		this.companyService = companyService;
	}
	
	@Autowired
	public void setCompanyMapper(CompanyDTOMapper companyMapper)
	{
		this.companyMapper = companyMapper;
	}
	
	@RequestMapping(params = {"id"}, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CompanyDTO getCompany(@RequestParam("id") int id)
	{
		return companyMapper.mapToDTO(companyService.getCompany(id));
	}
	
	@RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CompanyDTO getCompany(@RequestParam("name") String name)
	{
		return companyMapper.mapToDTO(companyService.getCompany(name));
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<CompanyDTO> getAllCompanies()
	{
		return companyMapper.mapToDTOList(companyService.getAllCompanies());
	}
	
	@RequestMapping(params = {"id"}, method = RequestMethod.DELETE)
	public void deleteCompany(@RequestParam("id") int id)
	{
		companyService.removeCompany(id);
	}
	
	@RequestMapping(params = {"name"}, method = RequestMethod.DELETE)
	public void deleteCompany(@RequestParam("name") String name)
	{
		companyService.removeCompany(name);
	}
}
