package service;

import java.util.List;

import model.Company;
import persistence.CompanyRequestHandler;
import persistence.ComputerRequestHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

@Component("companyService")
@Scope("singleton")
public class CompanyService {

	//private static CompanyService instance;
	private ComputerRequestHandler computerRequestHandler;
	private CompanyRequestHandler companyRequestHandler;
	
	@Autowired
	public CompanyService(ComputerRequestHandler computerRequestHandler,CompanyRequestHandler companyRequestHandler)
	{
		this.companyRequestHandler = companyRequestHandler;
		this.computerRequestHandler = computerRequestHandler;
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
