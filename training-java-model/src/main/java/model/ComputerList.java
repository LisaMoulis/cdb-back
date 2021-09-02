package model;

import java.util.*;

/**
 * Class ComputerLists :
 * Keeper of the computers list
 * @author Lisa
 */
public class ComputerList {

	private List<Computer> computers;
	private int page = 1;
	private int size = 10;
	
	
	public ComputerList()
	{
	}
	
	public ComputerList(int page, int size)
	{
		this.page = page;
		this.size = size;
	}
	
	public List<Computer> getComputers()
	{
		return computers;
	}
	
	public void setComputers(List<Computer> list)
	{
		this.computers = list;
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
		return "ComputerList [computers=" + computers + "]";
	}
	
}
