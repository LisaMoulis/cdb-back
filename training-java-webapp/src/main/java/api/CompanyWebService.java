package api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.CompanyDTO;
import mapper.CompanyDTOMapper;
import model.CompanyList;
import service.CompanyService;
import service.PageService;


@RestController
@RequestMapping("/service/companies")
public class CompanyWebService {

	private CompanyService companyService;
	private CompanyDTOMapper companyMapper;
	private PageService pageService;
	
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
	
	@Autowired
	public void setPageService(PageService pageService)
	{
		this.pageService = pageService;
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
	
	@RequestMapping(params = {"page","size"},method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<CompanyDTO> getAllCompanies(@RequestParam("page") int page, @RequestParam("size") int size,@RequestParam(name = "search", defaultValue = "") String search,@RequestParam(name = "order", defaultValue = "company.id") String order, @RequestParam(name = "direction", defaultValue = "asc") String dir)
	{
		CompanyList list = new CompanyList();
		list.setPage(page);
		list.setSize(size);
		return companyMapper.mapToDTOList(pageService.getCompanyList(list,search, order, dir));
	}
	
	@RequestMapping(value="/nb", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public int getNb(@RequestParam(name = "search", defaultValue="") String search)
	{
		return companyService.getNbCompanies(search);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void addComputer(@RequestBody @Valid CompanyDTO company)
	{
		companyService.createCompany(companyMapper.mapToCompany(company));
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
