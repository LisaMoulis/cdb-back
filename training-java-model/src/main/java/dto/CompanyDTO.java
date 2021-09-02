package dto;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class CompanyDTO {

	@Id
	private int id = -1;
	@Column
	private String name;
	
	public CompanyDTO(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public CompanyDTO(String name)
	{
		this.name = name;
	}
	
	public CompanyDTO() {}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public boolean equals(Object c)
	{
		CompanyDTO totest = (CompanyDTO) c;
		if (this.id == totest.getId() && this.name.equals(totest.getName()))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", name=" + name + "]";
	}
}
