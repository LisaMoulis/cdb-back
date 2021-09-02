package persistence;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

/**
 * Class DBConnection :
 * Handle the connection
 * @author Lisa
 */

@Repository
@Scope("singleton")
public class DBConnection {

	//private static DBConnection instance;
	private static Connection connection;
	private static final Logger logger =  LoggerFactory.getLogger(DBConnection.class);
	//private static HikariConfig config = new HikariConfig("/datasource.properties");
    
	
	private HikariDataSource dataSource;  
    
    @Autowired
	public DBConnection(HikariDataSource dataSource)
	{
		this.dataSource = dataSource;
		/*config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource  = new HikariDataSource(config);*/
	}
	
	/**
	 * @return instance	The unique instance of the class
	 */
	/*public static DBConnection getInstance()
	{
		//Create the instance if it's not existing
		if (instance == null)
		{
			instance = new DBConnection();
		}
		return instance;
	}*/
	
	public Logger getLogger()
	{
		return logger;
	}

	/**
	 * Close the connection to the database
	 */
	public void close()
	{
		//Close the connection if it's existing
		if (connection != null)
		{
			try {
				connection.close();
				logger.debug("Connection closed.");
			} catch (SQLException e) {
				System.out.println("Impossible to close the connection. ");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return The connection to the database
	 */
	public Connection getConnection()
	{
		//Create the instance if it's not existing
		/*if (instance == null)
		{
			instance = new DBConnection();
		}*/
		
		try {
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			
			logger.debug("Connection to the database etablished.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Failed to establish a connection to the database.");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	/*@Override
	public void finalize()
	{
		//Close the connection as soon as the instance is available to the garbage collector taking
		try {
			if (!connection.isClosed())
			{
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
}
