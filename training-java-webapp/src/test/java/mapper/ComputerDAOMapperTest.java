package mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.junit.Test;
import org.mockito.Mockito;

import model.Company;
import model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerDAOMapperTest {
	
	@Autowired
	private ComputerDAOMapper computerDAOMapper;
	
	@Test
	public void testToComputer() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException
	{
		ResultSet result = Mockito.mock(ResultSet.class);

		Computer c;
		Mockito.when(result.getString("name")).thenReturn("test");
		Mockito.when(result.getBytes("introduced")).thenReturn(new String("2021-01-01").getBytes());
		Mockito.when(result.getString("introduced")).thenReturn("2021-01-01 00:00:00");
		Mockito.when(result.getTimestamp("introduced")).thenReturn(Timestamp.valueOf("2021-01-01 00:00:00"));
		Mockito.when(result.getBytes("discontinued")).thenReturn(new String("2021-02-02").getBytes());
		Mockito.when(result.getString("discontinued")).thenReturn("2021-02-02 00:00:00");
		Mockito.when(result.getTimestamp("discontinued")).thenReturn(Timestamp.valueOf("2021-02-02 00:00:00"));
		Mockito.when(result.getString("company.name")).thenReturn("testcompany");
		Mockito.when(result.getInt("computer.id")).thenReturn(3);
		c = computerDAOMapper.mapRow(result,1);
		assertEquals("test",c.getName());
		assertEquals(LocalDate.of(2021, 1, 1),c.getIntroduced());
		assertEquals(LocalDate.of(2021, 2, 2),c.getDiscontinued());
		assertEquals("testcompany",c.getCompany().getName());
		
	}
	
	@Test
	public void testToUpdate() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Computer computer = new Computer(1,"test",LocalDate.of(2021,1,1),LocalDate.of(2021,2,2),new Company(3,"testcompany"));
		String request = computerDAOMapper.mapToUpdate(computer,3);
		assertEquals("`id`='1', `name`='test', `introduced`='2021-01-01 00:00:00.0', `discontinued`='2021-02-02 00:00:00.0', `company_id`='3'",request);

	}
	
	@Test
	public void testToCreate() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Computer computer = new Computer(1,"test",LocalDate.of(2021,1,1),LocalDate.of(2021,2,2),new Company(3,"testcompany"));
		String request = computerDAOMapper.mapToCreate(computer,3);
		assertEquals("(`name`, `introduced`, `discontinued`, `company_id`) VALUES ('test', '2021-01-01 00:00:00.0', '2021-02-02 00:00:00.0', 3)",request);

	}
}
