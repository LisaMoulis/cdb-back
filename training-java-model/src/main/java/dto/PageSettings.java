package dto;

public class PageSettings {

	private String filter = "computer.name";
	private String order = "asc";
	
	public String getOrder()
	{
		return this.order;
	}
	
	public void setOrder(String o)
	{
		this.order = o;
	}
	
	public String getFilter()
	{
		return this.filter;
	}
	
	public void setFilter(String str)
	{
		this.filter = str;
	}
}
