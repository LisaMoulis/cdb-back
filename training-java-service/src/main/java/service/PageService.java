package service;

import java.util.List;

import model.*;
import persistence.CompanyRequestHandler;
import persistence.ComputerRequestHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

@Component("pagerService")
@Scope("singleton")
public class PageService {

	//private static PageService instance;
	@Autowired
	private ComputerRequestHandler computerRequestHandler;
	@Autowired
	private CompanyRequestHandler companyRequestHandler;
	
	public PageService()
	{}
	
	public List<Computer> getPage(ComputerList page, String search, String column, String sense)
	{
		page.setComputers(computerRequestHandler.getPage(page.getSize(),page.getOffset(),search,column,sense.toUpperCase()));
		return page.getComputers();
	}
	
	public int getNbComputers(String search)
	{
		return computerRequestHandler.getNbComputers(search);
	}

	public List<Company> getCompanyList(CompanyList page, String search, String column, String dir) {
		page.setCompanies(companyRequestHandler.getPage(page.getSize(),page.getOffset(), search, column, dir));
		return page.getCompanies();
	}
	
	public int getNbCompanies(String search)
	{
		return companyRequestHandler.getNbCompanies(search);
	}
	
}
