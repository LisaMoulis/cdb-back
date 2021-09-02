package mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import dto.CompanyDTO;
import model.Company;

@Component
public class CompanyDTOMapper {

	public Company mapToCompany(CompanyDTO company)
	{
		return new Company(company.getId(),company.getName());
	}
	

	public CompanyDTO mapToDTO(Company company)
	{
		return new CompanyDTO(company.getId(),company.getName());
	}

	
	public List<CompanyDTO> mapToDTOList(List<Company> companies)
	{
		List<CompanyDTO> dto = (List<CompanyDTO>) companies.stream().map(e -> mapToDTO(e)).toList();
		return dto;
	}

	public List<Company> mapToCompanyList(List<CompanyDTO> dto) {
		List<Company> companies = (List<Company>) dto.stream().map(e -> mapToCompany(e)).toList();
		return companies;
	}
}
