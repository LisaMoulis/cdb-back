package service;

import java.util.List;

import javax.validation.Valid;

import model.Company;
import persistence.CompanyRequestHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

@Component("companyService")
@Scope("singleton")
public class CompanyService {

	//private static CompanyService instance;
	private CompanyRequestHandler companyRequestHandler;
	
	@Autowired
	public CompanyService(CompanyRequestHandler companyRequestHandler)
	{
		this.companyRequestHandler = companyRequestHandler;
	}
	
	/*public static CompanyService getInstance()
	{
		if (instance == null)
		{
			instance = new CompanyService();
		}
		return instance;
	}*/
	
	public Company getCompany(int id)
	{
		return companyRequestHandler.getCompany(id);
	}
	
	public Company getCompany(String name)
	{
		return companyRequestHandler.getCompany(name);
	}
	
	public void createCompany(@Valid Company company) throws RuntimeException
	{
		companyRequestHandler.createCompany(company);
	}
	
	public void removeCompany(int id)
	{
		companyRequestHandler.deleteCompany(id);
	}
	
	public void removeCompany(String name)
	{
		companyRequestHandler.deleteCompany(companyRequestHandler.getCompany(name).getId());
	}
	
	public List<Company> getAllCompanies()
	{
		return companyRequestHandler.getAllCompanies();
	}
}
