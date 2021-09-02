package persistence;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.RollbackException;

import model.Computer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

import dto.ComputerDTO;
import mapper.ComputerDTOMapper;

/**
 * Class ComputersRequesthandler :
 * Manage the SQL requests for the computers
 * @author Lisa
 */

@Repository
@Scope("singleton")
public class ComputerRequestHandler {

	private static final String GET_WITH_NAME = "from ComputerDTO c where c.name=:name";//"SELECT computer.id, computer.name,`company_id`,`introduced`,`discontinued`, company.name FROM `computer`, `company.name` LEFT JOIN `company` ON company_id = company.id WHERE computer.name=?";
	//private static final String GET_WITH_ID = "SELECT computer.id, computer.name,`company_id`,`introduced`,`discontinued`, company.name FROM `computer` LEFT JOIN `company` ON company_id = company.id WHERE computer.id=?";
	private static final String GET_PAGE = "SELECT computer.id, computer.name,`company_id`,`introduced`,`discontinued`, company.name FROM `computer` LEFT JOIN `company` ON company_id = company.id WHERE LOWER(computer.name) LIKE ? OR LOWER(company.name) LIKE ? ORDER BY "; 
	private static final String GET_NB_COMPUTERS = "SELECT COUNT(computer.id) FROM `computer` LEFT JOIN `company` ON company_id = company.id WHERE LOWER(computer.name) LIKE ? OR LOWER(company.name) LIKE ?"; 

	private SessionFactory sessionFactory;
	private ComputerDTOMapper computerMapper;
	
	@Autowired
	public ComputerRequestHandler(SessionFactory sessionFactory,ComputerDTOMapper computerMapper)
	{
		this.sessionFactory = sessionFactory;
		this.computerMapper = computerMapper;
	}
	
	/**
	 * @param id	Identified of a computer
	 * @return The computer found
	 */
	public Computer getComputer(int id)
	{
		Session session = sessionFactory.openSession();
		ComputerDTO computer = session.find(ComputerDTO.class, id);
		session.close();
		return computerMapper.mapToComputer(computer);
	}
	
	/**
	 * @param name	Name of a computer
	 * @return The computer found
	 */
	public Computer getComputer(String name)
	{			
		Session session = sessionFactory.openSession();
		Query<ComputerDTO> query = session.createQuery(GET_WITH_NAME, ComputerDTO.class);
	    query.setParameter("name", name);
	    ComputerDTO computer = query.uniqueResult();
	    session.close();
		return computerMapper.mapToComputer(computer);
	}
	
	/**
	 * @return The list of all the computers
	 */
	public List<Computer> getAllComputers()
	{
		Session session = sessionFactory.openSession();
		Query<ComputerDTO> query = session.createQuery("from ComputerDTO", ComputerDTO.class);
	    List<ComputerDTO> computers = query.getResultList();
	    session.close();
		return computerMapper.mapToComputerList(computers);
		//return jdbcTemplate.query("SELECT * FROM `computer` LEFT JOIN `company` ON company_id = company.id", this.computerDAOMapper);
	}
	
	public List<Computer> getPage(int size, int offset, String search, String column, String sense)
	{
		Session session = sessionFactory.openSession();
		String str = GET_PAGE + column + " " + sense;
		try {
			int id = Integer.valueOf(search);
			str = str+ " OR c.id = " + id;
		}
		catch (Exception e)
		{}
			
		str = str + " LIMIT ? OFFSET ?";

		@SuppressWarnings("unchecked")
		Query<ComputerDTO> query = session.createSQLQuery(str).addEntity(ComputerDTO.class);
		
		query.setParameter(1, "%"+search.toLowerCase()+"%");
		query.setParameter(2, "%"+search.toLowerCase()+"%");
		query.setParameter(3, size);
		query.setParameter(4, offset);
		List<ComputerDTO> page = query.getResultList();
		session.close();
		return computerMapper.mapToComputerList(page);
	}

	public int getNbComputers(String search)
	{
		String str = GET_NB_COMPUTERS;
		try {
			int id = Integer.valueOf(search);
			str = str+ " OR computer.id = " + id;
		}
		catch (Exception e)
		{}
		Session session = sessionFactory.openSession();
		//dbConnection.getLogger().info("Nb computers : " + result.getInt(1));
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createSQLQuery(str);
		
		query.setParameter(1, "%"+search.toLowerCase()+"%");
		query.setParameter(2, "%"+search.toLowerCase()+"%");
		int nb = query.uniqueResult().intValue();
		session.close();
		return nb;
	}
	
	/**
	 * @param computer	Computer to create
	 */
	public void createComputer(Computer computer, int company_id)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
		  session.save(computerMapper.mapToDTO(computer));
		  transaction.commit();
		} catch (RollbackException t) {
		  transaction.rollback();
		  throw t;
		}
		session.close();
	}
	
	/**
	 * @param computer	Computer to update
	 */
	public void updateComputer(Computer computer,int company_id)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			System.out.println(computerMapper.mapToDTO(computer));
		  session.update(computerMapper.mapToDTO(computer));
		  transaction.commit();
		} catch (RollbackException t) {
		  transaction.rollback();
		  throw t;
		}
		session.close();
		//jdbcTemplate.update("UPDATE `computer` SET "+ computerDAOMapper.mapToUpdate(computer,company_id) + "WHERE id=?",new Object[] {computer.getId()}, new int[] {Types.INTEGER});
	}
	
	/**
	 * @param id	Identifier of the computer to delete
	 */
	public void deleteComputer(int id)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(computerMapper.mapToDTO( getComputer(id)));
			transaction.commit();
		} catch (RollbackException t) {
			transaction.rollback();
			throw t;
		}
		session.close();
	}
	
	/**
	 * @param name Name of the computer to delete
	 */
	public void deleteComputer(String name)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
		  Query<?> query = session.createQuery("delete from ComputerDTO where name = :name");
		  query.setParameter("name", name);
		  query.executeUpdate();
		  transaction.commit();
		} catch (RollbackException t) {
		  transaction.rollback();
		  throw t;
		}
		session.close();
	}
	
}
