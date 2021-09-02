package persistence;


import java.util.List;

import javax.persistence.RollbackException;

import mapper.CompanyDTOMapper;

import model.Company;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import dto.CompanyDTO;


/**
 * Class CompanyRequesthandler :
 * Manage the SQL requests for the companies
 * @author Lisa
 */

@Repository
public class CompanyRequestHandler {

	/**
	 * @param id	Identifier of a company
	 * @return The company found
	 */
	
	private SessionFactory sessionFactory;
	private CompanyDTOMapper companyMapper;
	
	@Autowired
	public CompanyRequestHandler(SessionFactory sessionFactory, CompanyDTOMapper companyMapper)
	{
		this.sessionFactory = sessionFactory;
		this.companyMapper = companyMapper;
	}
	
	public Company getCompany(int id)
	{
		Session session = sessionFactory.openSession();
		CompanyDTO company = session.find(CompanyDTO.class, id);
		session.close();
		return companyMapper.mapToCompany(company);
	}
	
	public Company getCompany(String name)
	{
		Session session = sessionFactory.openSession();
		Query<CompanyDTO> query = session.createQuery("from CompanyDTO c where c.name=:name", CompanyDTO.class);
	    query.setParameter("name", name);
	    CompanyDTO company = query.uniqueResult();
	    session.close();
		return companyMapper.mapToCompany(company);
	}
	
	/**
	 * @return All the companies in the database
	 */
	public List<Company> getAllCompanies()
	{
		Session session = sessionFactory.openSession();
		Query<CompanyDTO> query = session.createQuery("from CompanyDTO c", CompanyDTO.class);
		List<CompanyDTO> companies = query.getResultList();
		session.close();
		return companyMapper.mapToCompanyList(companies);
	}
	
	@Transactional
	public void deleteCompany(int id)
	{
		//jdbcTemplate.update("DELETE FROM `computer` WHERE company_id=?",new Object[] { id }, new int[] { Types.INTEGER});
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
		  Query<?> queryComputer = session.createQuery("delete from ComputerDTO where company_id = :id");
		  queryComputer.setParameter("id", id);
		  queryComputer.executeUpdate();
		  Query<?> queryCompany  = session.createQuery("delete from CompanyDTO where id = :id");
		  queryCompany.setParameter("id", id);
		  queryCompany.executeUpdate();

		  transaction.commit();
		} catch (RollbackException t) {
		  transaction.rollback();
		  throw t;
		}
		//session.remove(id);
		session.close();
	}
}
