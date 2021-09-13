package persistence;


import java.math.BigInteger;
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
	private static final String GET_PAGE  = "SELECT * FROM `company` WHERE LOWER(company.name) LIKE ? ORDER BY ";
	private static final String GET_NB_COMPANIES = "SELECT COUNT(company.id) FROM `company` WHERE LOWER(company.name) LIKE ?"; 

	
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
		if (company != null)
		{
			return companyMapper.mapToCompany(company);
		}
		return null;
	}
	
	public Company getCompany(String name)
	{
		Session session = sessionFactory.openSession();
		Query<CompanyDTO> query = session.createQuery("from CompanyDTO c where c.name=:name", CompanyDTO.class);
	    query.setParameter("name", name);
	    CompanyDTO company = query.uniqueResult();
	    session.close();
	    if (company != null)
		{
			return companyMapper.mapToCompany(company);
		}
		return null;
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
	
	public List<Company> getPage(int size, int offset, String search , String column, String sense)
	{
		Session session = sessionFactory.openSession();
		String str = GET_PAGE + column + " " + sense;
		try {
			int id = Integer.valueOf(search);
			str = str+ " OR company.id = " + id;
		}
		catch (Exception e)
		{}
			
		str = str + " LIMIT ? OFFSET ?";

		@SuppressWarnings("unchecked")
		Query<CompanyDTO> query = session.createSQLQuery(str).addEntity(CompanyDTO.class);
		
		query.setParameter(1, "%"+search.toLowerCase()+"%");
		//query.setParameter(2, "%"+search.toLowerCase()+"%");
		query.setParameter(2, size);
		query.setParameter(3, offset);
		List<CompanyDTO> page = query.getResultList();
		session.close();
		return companyMapper.mapToCompanyList(page);
	}
	
	public int getNbCompanies(String search)
	{
		String str = GET_NB_COMPANIES;
		/*try {
			int id = Integer.valueOf(search);
			str = str+ " OR computer.id = " + id;
		}
		catch (Exception e)
		{}*/
		Session session = sessionFactory.openSession();
		//dbConnection.getLogger().info("Nb computers : " + result.getInt(1));
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createSQLQuery(str);
		
		query.setParameter(1, "%"+search.toLowerCase()+"%");
		//query.setParameter(2, "%"+search.toLowerCase()+"%");
		int nb = query.uniqueResult().intValue();
		session.close();
		return nb;
	}
	
	public void createCompany(Company company)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
		  session.save(companyMapper.mapToDTO(company));
		  transaction.commit();
		} catch (RollbackException t) {
		  transaction.rollback();
		  throw t;
		}
		session.close();
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
