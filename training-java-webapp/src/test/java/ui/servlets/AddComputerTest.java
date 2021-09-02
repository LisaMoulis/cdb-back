package ui.servlets;
/*
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import builder.ComputerDTOBuilder;
import dto.ComputerDTO;
import mapper.ComputerDTOMapper;
import model.Company;
import model.Computer;
import service.CompanyService;
import service.ComputerService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class AddComputerTest {

	private AddComputer servlet = new AddComputer(); 
	private CompanyService companyService = Mockito.mock(CompanyService.class);
	private ComputerService computerService = Mockito.mock(ComputerService.class);

	
	private RequestDispatcher dispatcher = mock(RequestDispatcher.class);
	
	@Before
	public void setInjections() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Mockito.when(companyService.getCompany(3)).thenReturn(new Company(3,"testcompany"));
		Mockito.when(companyService.getCompany("testcompany")).thenReturn(new Company(3,"testcompany"));
		
		servlet.setCompanyService(companyService);
		servlet.setComputerService(computerService);
        servlet.setComputerDTOMapper(new ComputerDTOMapper(companyService));
	}
	
	@Test
	public void testEmpty() throws IOException, ServletException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);       
	    HttpServletResponse response = mock(HttpServletResponse.class);
	    
	    Mockito.when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);
		Mockito.when(request.getParameter("computerName")).thenReturn("test");
		ComputerDTO dto = new ComputerDTOBuilder().setName("test").build();
	    servlet.doPost(request, response,dto);
	    ArgumentCaptor<Computer> c = ArgumentCaptor.forClass(Computer.class);
		Mockito.verify(computerService).createComputer(c.capture());
		assertEquals("test",c.getValue().getName());
		assertNull(c.getValue().getIntroduced());
		assertNull(c.getValue().getDiscontinued());
		assertNull(c.getValue().getCompany());
	}
	
	@Test
	public void testFull() throws IOException, ServletException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);       
	    HttpServletResponse response = mock(HttpServletResponse.class);
	    
	    Mockito.when(request.getRequestDispatcher(Mockito.anyString())).thenReturn(dispatcher);
		Mockito.when(request.getParameter("computerName")).thenReturn("test");
		ComputerDTO dto = new ComputerDTOBuilder().setName("test").setIntroduced("2020-01-01").setDiscontinued("2020-02-02").setCompany("testcompany").setCompanyId(3).build();
	    servlet.doPost(request, response,dto);
	    ArgumentCaptor<Computer> c = ArgumentCaptor.forClass(Computer.class);
		Mockito.verify(computerService).createComputer(c.capture());
		assertEquals("test",c.getValue().getName());
		assertNotNull(c.getValue().getIntroduced());
		assertNotNull(c.getValue().getDiscontinued());
		assertEquals("testcompany",c.getValue().getCompany());
	}
}*/
