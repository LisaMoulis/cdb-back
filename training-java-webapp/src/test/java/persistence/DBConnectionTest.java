package persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
/*
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class DBConnectionTest {

	@Autowired
	private DBConnection dbConnection;
	
	@Test
	public void testOpen() throws SQLException
	{
		Connection connection = dbConnection.getConnection();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
        connection.close();
	}
	
	@Test
	public void testClose() throws SQLException
	{extends WebSecurityConfigurerAdapter
        Connection connection = dbConnection.getConnection();
        assertNotNull(connection);
        dbConnection.close();
        assertTrue(connection.isClosed());
	}
}*/
