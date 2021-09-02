package service;
/*
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zaxxer.hikari.HikariDataSource;

import mapper.ComputerDAOMapper;
import model.Company;
import model.Computer;
import persistence.CompanyRequestHandler;
import persistence.ComputerRequestHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerServiceTest {

	
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
		Mockito.when(connection.createStatement()).thenReturn(query);
		
		Mockito.when(dataSource.getConnection()).thenReturn(connection);
	}

	@Test
	public void testGetComputerByName()
	{
		CompanyRequestHandler companyHandler = new CompanyRequestHandler(dataSource);
		ComputerRequestHandler computerHandler = new ComputerRequestHandler(dataSource,new ComputerDAOMapper());
		Computer c = new ComputerService(computerHandler,companyHandler).getComputer("test");
		assertEquals("test",c.getName());
		assertEquals(LocalDate.of(2021, 1, 1),c.getIntroduced());
		assertEquals(LocalDate.of(2021, 2, 2),c.getDiscontinued());
		assertEquals("testcompany",c.getCompany());
	}	
	
	@Test
	public void testGetComputerById()
	{		
		CompanyRequestHandler companyHandler = new CompanyRequestHandler(dataSource);
		ComputerRequestHandler computerHandler = new ComputerRequestHandler(dataSource,new ComputerDAOMapper());
		Computer c = new ComputerService(computerHandler,companyHandler).getComputer(3);
		assertEquals("test",c.getName());
		assertEquals(LocalDate.of(2021, 1, 1),c.getIntroduced());
		assertEquals(LocalDate.of(2021, 2, 2),c.getDiscontinued());
		assertEquals("testcompany",c.getCompany());
	}
	
	@Test
	public void testDeleteComputerWithId() throws SQLException
	{
		CompanyRequestHandler companyHandler = new CompanyRequestHandler(dataSource);
		ComputerRequestHandler computerHandler = Mockito.mock(ComputerRequestHandler.class);
		new ComputerService(computerHandler,companyHandler).removeComputer(3);
		ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
		Mockito.verify(computerHandler).deleteComputer(argument.capture());
		assertEquals(3,argument.getValue().intValue());	
	}
	
	@Test
	public void testDeleteComputerWithName() throws SQLException
	{
		CompanyRequestHandler companyHandler = new CompanyRequestHandler(dataSource);
		ComputerRequestHandler computerHandler = new ComputerRequestHandler(dataSource,new ComputerDAOMapper());
		new ComputerService(computerHandler,companyHandler).removeComputer("test");
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		Mockito.verify(query).setString(Mockito.anyInt(),argument.capture());
		assertEquals("test",argument.getValue());	
	}
	
	@Test
	public void testDeleteAllComputers() throws SQLException
	{
		String [] args = {"3"};
		CompanyRequestHandler companyHandler = new CompanyRequestHandler(dataSource);
		ComputerRequestHandler computerHandler = Mockito.mock(ComputerRequestHandler.class);
		new ComputerService(computerHandler,companyHandler).removeSelectedComputer(args);
		ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
		Mockito.verify(computerHandler,Mockito.atLeastOnce()).deleteComputer(argument.capture());
		//List<String> values = argument.getAllValues()
		assertEquals(3,argument.getValue().intValue());	
	}
	
	@Test
	public void testCreate() throws SQLException
	{
		Computer computer = new Computer(1,"test",LocalDate.of(2021,1,1),LocalDate.of(2021,2,2),"testcompany");
		CompanyRequestHandler companyHandler = Mockito.mock(CompanyRequestHandler.class);
		Company comp = new Company(3,"testcompany");
		Mockito.when(companyHandler.getCompany("testcompany")).thenReturn(comp);
		Mockito.when(companyHandler.getCompany(3)).thenReturn(comp);
		
		ComputerRequestHandler computerHandler = Mockito.mock(ComputerRequestHandler.class);
		new ComputerService(computerHandler,companyHandler).createComputer(computer);
		Mockito.verify(computerHandler,Mockito.atLeastOnce()).createComputer(computer, 3);
	}
	
	@Test
	public void testUpdate() throws SQLException
	{
		Computer computer = new Computer(1,"test",LocalDate.of(2021,1,1),LocalDate.of(2021,2,2),"testcompany");
		ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
		CompanyRequestHandler companyHandler = Mockito.mock(CompanyRequestHandler.class);
		Company comp = new Company(3,"testcompany");
		Mockito.when(companyHandler.getCompany("testcompany")).thenReturn(comp);
		Mockito.when(companyHandler.getCompany(3)).thenReturn(comp);
		ComputerRequestHandler computerHandler = new ComputerRequestHandler(dataSource,new ComputerDAOMapper());
		new ComputerService(computerHandler,companyHandler).updateComputer(computer);
		Mockito.verify(connection,Mockito.atLeastOnce()).prepareStatement(argument.capture());
		
		assertEquals("UPDATE `computer` SET " + new ComputerDAOMapper().mapToUpdate(computer,3) + "WHERE id=?", argument.getValue());
	}
}*/
