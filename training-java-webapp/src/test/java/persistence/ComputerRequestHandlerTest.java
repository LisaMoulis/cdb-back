package persistence;
/*
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zaxxer.hikari.HikariDataSource;

import mapper.ComputerDAOMapper;
import model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerRequestHandlerTest {
	
	private ComputerRequestHandler computerRequestHandler;
	
	private Connection connection =  Mockito.mock(Connection.class);
	private ResultSet result = Mockito.mock(ResultSet.class);
	private PreparedStatement query = Mockito.mock(PreparedStatement.class);
	private HikariDataSource dataSource = Mockito.mock(HikariDataSource.class);
	
	@Before
	public void setMethods() throws NoSuchFieldException, SecurityException, SQLException, IllegalArgumentException, IllegalAccessException
	{
		Mockito.when(result.getString("name")).thenReturn("test");
		Mockito.when(result.getBytes("introduced")).thenReturn(new String("2021-01-01").getBytes());
		Mockito.when(result.getString("introduced")).thenReturn("2021-01-01 00:00:00");
		Mockito.when(result.getTimestamp("introduced")).thenReturn(Timestamp.valueOf("2021-01-01 00:00:00"));
		Mockito.when(result.getBytes("discontinued")).thenReturn(new String("2021-02-02").getBytes());
		Mockito.when(result.getString("discontinued")).thenReturn("2021-02-02 00:00:00");
		Mockito.when(result.getTimestamp("discontinued")).thenReturn(Timestamp.valueOf("2021-02-02 00:00:00"));
		Mockito.when(result.getString("company.name")).thenReturn("testcompany");
		Mockito.when(result.getInt("computer.id")).thenReturn(3);
		Mockito.when(result.next()).thenReturn(true).thenReturn(false);
		
		Mockito.when(query.executeQuery()).thenReturn(result);
		Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(query);
		
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
		computerRequestHandler = new ComputerRequestHandler(dataSource, new ComputerDAOMapper());
	}
	
	@Test
	public void testGetComputerByName()
	{		
		Computer c = computerRequestHandler.getComputer("test");
		assertEquals("test",c.getName());
		assertEquals(LocalDate.of(2021, 1, 1),c.getIntroduced());
		assertEquals(LocalDate.of(2021, 2, 2),c.getDiscontinued());
		assertEquals("testcompany",c.getCompany());
	}	
	
	@Test
	public void testGetComputerById()
	{		
		Computer c = computerRequestHandler.getComputer(3);
		assertEquals("test",c.getName());
		assertEquals(LocalDate.of(2021, 1, 1),c.getIntroduced());
		assertEquals(LocalDate.of(2021, 2, 2),c.getDiscontinued());
		assertEquals("testcompany",c.getCompany());
	}
	
	/*@Test
	public void testAllComputers()
	{		
		List<Computer> alls = computerRequestHandler.getAllComputers();
		Computer c = alls.get(3);
		assertEquals("test",c.getName());
		assertEquals(LocalDate.of(2021, 1, 1),c.getIntroduced());
		assertEquals(LocalDate.of(2021, 2, 2),c.getDiscontinued());
		assertEquals("testcompany",c.getCompany());
	}
	
}*/
