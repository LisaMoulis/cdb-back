package builder;

import dto.ComputerDTO;

public class ComputerDTOBuilder {

	private int id = -1;
	private String name = "";
	private String introduced;
	private String discontinued;
	private String company = "";
	private int companyId = -1;
	
	public ComputerDTOBuilder setId(int id)
	{
		this.id = id;
		return this;
	}
	
	public ComputerDTOBuilder setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public ComputerDTOBuilder setIntroduced(String introduced)
	{
		this.introduced = introduced;
		return this;
	}
	
	public ComputerDTOBuilder setDiscontinued(String discontinued)
	{
		this.discontinued = discontinued;
		return this;
	}
	
	public ComputerDTOBuilder setCompany(String name)
	{
		this.company = name;
		return this;
	}
	
	
	public ComputerDTOBuilder setCompanyId(int id)
	{
		this.companyId = id;
		return this;
	}
	
	public ComputerDTO build()
	{
		return new ComputerDTO(id, name, introduced, discontinued, company, companyId);
	}
		
}
