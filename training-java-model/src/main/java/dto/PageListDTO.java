package dto;

import java.util.List;

import javax.validation.constraints.*;

public class PageListDTO {
	
	@Min(0)
	private int page = 1;
	@Min(1)
	private int size = 10;
	@NotNull
	private String search = "";
	private int nbComputers = 1;
	
	private List<ComputerDTO> computers;
	
	public int getPage()
	{
		return this.page;
	}
	
	public void setPage(int page)
	{
		this.page = page;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public void setSize(int size)
	{
		this.size = size;
	}
	
	public int getNbPages()
	{
		int nbComputers = getNbComputers();
		if (nbComputers%size == 0)
		{
			return nbComputers/size;
		}
		return nbComputers/size + 1;
	}
	
	public List<ComputerDTO> getComputers()
	{
		return this.computers;
	}
	
	public void setComputers(List<ComputerDTO> computers)
	{
		this.computers = computers;
	}
	
	public int getNbComputers()
	{
		return this.nbComputers;
	}
	
	public void setNbComputers(int nb)
	{
		this.nbComputers = nb;
	}
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String s)
	{
		this.search = s;
	}
}
