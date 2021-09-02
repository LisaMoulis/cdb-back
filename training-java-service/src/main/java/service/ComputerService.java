package service;

import model.Company;
import model.Computer;
import persistence.CompanyRequestHandler;
import persistence.ComputerRequestHandler;
import validation.Validator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

@Component("computerService")
@Scope("singleton")
public class ComputerService {

	//private static ComputerService instance;
	
	private ComputerRequestHandler computerRequestHandler;
	
	@Autowired
	public ComputerService(ComputerRequestHandler computerRequestHandler,CompanyRequestHandler companyRequestHandler)
	{
		this.computerRequestHandler = computerRequestHandler;
	}
	
	public Computer getComputer(int id)
	{
		return computerRequestHandler.getComputer(id);
	}
	
	public Computer getComputer(String name)
	{
		return computerRequestHandler.getComputer(name);
	}
	
	public void removeComputer(int id)
	{
		computerRequestHandler.deleteComputer(id);
	}
	
	public void removeComputer(String name)
	{
		computerRequestHandler.deleteComputer(name);
	}
	
	public void removeSelectedComputer(String[] computers)
	{
		for (String nb : computers)
		{
			computerRequestHandler.deleteComputer(Integer.valueOf(nb));
		}
	}
	
	public void createComputer(@Valid Computer computer) throws RuntimeException
	{	
		Validator.validate(computer);
		int company_id = -1;

		Company company = computer.getCompany();
		if (company != null)
		{
			company_id = company.getId();
		}
		
		computerRequestHandler.createComputer(computer,company_id);
	}
	
	public void updateComputer(Computer computer) throws RuntimeException
	{
		Validator.validate(computer);
		int company_id = -1;

		Company company = computer.getCompany();
		if (company != null)
		{
			company_id = company.getId();
		}
		computerRequestHandler.updateComputer(computer,company_id);
	}
}
