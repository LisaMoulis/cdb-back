package mapper;

import dto.ComputerDTO;
import model.Company;
import model.Computer;
import service.CompanyService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerDTOMapperTest {

	private ComputerDTOMapper computerDTOMapper;
	
	@Before
	public void setMapper()
	{
		CompanyService service =  mock(CompanyService.class);
		Mockito.when(service.getCompany(3)).thenReturn(new Company(3,"testcompany"));
		Mockito.when(service.getCompany("testcompany")).thenReturn(new Company(3,"testcompany"));
		
		computerDTOMapper = new ComputerDTOMapper(service);
	}
	
	@Test
	public void testToComputer() throws Exception
	{
		ComputerDTO dto = new ComputerDTO(1,"test","2021-01-01","2021-02-02","testcompany",3);
		
		Computer c = computerDTOMapper.mapToComputer(dto);
		
		assertEquals("test",c.getName());
		assertEquals(LocalDate.of(2021, 1, 1),c.getIntroduced());
		assertEquals(LocalDate.of(2021, 2, 2),c.getDiscontinued());
		assertEquals("testcompany",c.getCompany().getName());
	}
	
	@Test
	public void testToDTO() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Computer c = new Computer(1,"test",LocalDate.of(2021,1,1),LocalDate.of(2021,2,2),new Company(3,"testcompany"));
		ComputerDTO dto = computerDTOMapper.mapToDTO(c);
		
		assertEquals("test",dto.getName());
		assertEquals("2021-01-01",dto.getIntroduced());
		assertEquals("2021-02-02",dto.getDiscontinued());
		assertEquals("testcompany",dto.getCompany());
	}
}
