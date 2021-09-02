package model;

public class Company {

	private int id = -1;
	private String name;
	
	public Company(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public Company(String name)
	{
		this.name = name;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public boolean equals(Object c)
	{
		Company totest = (Company) c;
		if (this.id == totest.getId() && this.name.equals(totest.getName()))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return this.name;
	}
}
