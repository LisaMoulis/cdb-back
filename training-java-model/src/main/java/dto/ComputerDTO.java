package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import validation.ValidateDates;

@Entity
@Table(name = "computer")
@ValidateDates(message = "Invalid date!")
public class ComputerDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message="The name is empty!")
	private String name = "";
	@Column(nullable=true)
	private String introduced;
	@Column(nullable=true)
	private String discontinued;

	@ManyToOne(optional = true)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "company_id",nullable=true)
	private CompanyDTO company;
	
	public ComputerDTO(int id, String name, String introduced, String discontinued, String company, int companyId)
	{
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = new CompanyDTO(companyId,company);
	}
	
	public ComputerDTO()
	{}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getIntroduced()
	{
		return this.introduced;
	}
	
	public String getDiscontinued()
	{
		return this.discontinued;
	}
	
	public CompanyDTO getCompany()
	{
		return this.company;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduced(String introduced) {
		if (introduced != null && !introduced.equals(""))
		{
			this.introduced = introduced;
		}
	}

	public void setDiscontinued(String discontinued) {
		if (discontinued != null && !discontinued.equals(""))
		{
			this.discontinued = discontinued;
		}
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null)
		{
			return false;
		}
		ComputerDTO dto = (ComputerDTO) o;
		if (dto.getId() == id && name.equals(dto.getName()) && introduced.equals(dto.getIntroduced()) && discontinued.equals(dto.getDiscontinued()) && company.equals(dto.getCompany()))
		{
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company=" + company + "]";
	}

}
