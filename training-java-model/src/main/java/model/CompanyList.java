package model;

import java.util.List;

/**
 * Class CompanyList :
 * Keeper of the companies list
 * @author Lisa
 */
public class CompanyList {

	private List<Company> companies;
	
	public CompanyList()
	{
	}
	
	/**
	 * @param id Identifier of a company
	 * @return The company found
	 */
	public List<Company> getCompanies()
	{
		return this.companies;
	}
	
	public void setCompanies(List<Company> list)
	{
		this.companies = list;
	}
	
	
	@Override
	public String toString() {
		return "CompanyList [companies=" + companies + "]";
	}
}
