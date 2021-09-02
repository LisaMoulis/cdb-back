package persistence;
import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dto.UserDTO;
import mapper.UserDTOMapper;
import model.User;

@Repository
public class UserDAO {

	private static final String GET_WITH_NAME = "from UserDTO u where username = :username";
	//private static final String GET_ALL = "from User u";
	
	private SessionFactory sessionFactory;
	private UserDTOMapper userMapper;
	
	@Autowired
	public UserDAO(SessionFactory sessionFactory, UserDTOMapper userMapper)
	{
		this.sessionFactory = sessionFactory;
		this.userMapper = userMapper;
	}
	
	public User getUser(String u)
	{
		Session session = sessionFactory.openSession();
		Query<UserDTO> query = session.createQuery(GET_WITH_NAME, UserDTO.class);
	    query.setParameter("username", u);
	    UserDTO user = query.uniqueResult();
	    session.close();
		return userMapper.mapToUser(user);
	}
	/*
	public List<User> getAllUser()
	{
		Session session = sessionFactory.openSession();
		Query<User> query = session.createQuery(GET_ALL, User.class);
	    List<User> users = query.getResultList();
	    session.close();
		return users;
	}
	*/
	public void registerUser(User user)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
		  session.save(userMapper.mapToDTO(user));
		  transaction.commit();
		} catch (RollbackException t) {
		  transaction.rollback();
		  throw t;
		}
		session.close();
	}
}
