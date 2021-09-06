package model;

import java.util.List;

/**
 * Class CompanyList :
 * Keeper of the companies list
 * @author Lisa
 */
public class CompanyList {

	private List<Company> companies;
	private int page = 1;
	private int size = 10;
	
	
	public CompanyList()
	{
	}
	
	public CompanyList(int page, int size)
	{
		this.page = page;
		this.size = size;
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
	
	public int getPage()
	{
		return this.page;
	}
	
	public void setPage(int p)
	{
		this.page = p;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public void setSize( int s)
	{
		this.size = s;
	}
	
	public int getOffset()
	{
		return (this.page-1)*size;
	}
	
	@Override
	public String toString() {
		return "CompanyList [companies=" + companies + "]";
	}
}
