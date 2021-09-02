package builder;

import java.time.LocalDate;

import model.Company;
import model.Computer;

public class ComputerBuilder {

	private int id = -1;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;

	
	public ComputerBuilder setName(String name)
	{
		this.name = name;
		return this;
	}
	
	public ComputerBuilder setIntroduced(LocalDate introduced)
	{
		this.introduced = introduced;
		return this;
	}
	
	public ComputerBuilder setDiscontinued(LocalDate discontinued)
	{
		this.discontinued = discontinued;
		return this;
	}
	
	public ComputerBuilder setCompany(Company company)
	{
		this.company = company;
		return this;
	}
	
	public ComputerBuilder setId(int id)
	{
		this.id = id;
		return this;
	}
	
	public Computer build()
	{
		return new Computer(id, name, introduced, discontinued, company);
	}
}
