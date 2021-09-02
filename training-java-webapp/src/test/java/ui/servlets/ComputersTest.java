package ui.servlets;
/*
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import dto.PageListDTO;
import dto.PageSettings;
import mapper.ComputerDTOMapper;
import service.PageService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputersTest {
	
	private Computers servlet = new Computers(); 
	@Autowired
	private PageService pageService;
	@Autowired
	private ComputerDTOMapper computerDTOMapper;
	
	
	private RequestDispatcher dispatcher = mock(RequestDispatcher.class);
	private HttpSession session = new HttpSession()
			{
				private HashMap<String,Object> attributes = new HashMap<String,Object>();

				@Override
				public long getCreationTime() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public String getId() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public long getLastAccessedTime() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public ServletContext getServletContext() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void setMaxInactiveInterval(int interval) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public int getMaxInactiveInterval() {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public HttpSessionContext getSessionContext() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Object getAttribute(String name) {
					return attributes.get(name);
				}

				@Override
				public Object getValue(String name) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Enumeration<String> getAttributeNames() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String[] getValueNames() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void setAttribute(String name, Object value) {
					attributes.put(name, value);
				}

				@Override
				public void putValue(String name, Object value) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void removeAttribute(String name) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void removeValue(String name) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void invalidate() {
					// TODO Auto-generated method stub
					
				}

				@Override
				public boolean isNew() {
					// TODO Auto-generated method stub
					return false;
				}
			};
	
			
	@Before
	public void setInjections() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
        servlet.setPageService(pageService);
        servlet.setComputerDTOMapper(computerDTOMapper);
	}
	
	@Test
	public void testEmpty() throws IOException, ServletException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);  
	    
	    Mockito.when(request.getSession()).thenReturn(session);
	    Mockito.when(request.getRequestDispatcher("/WEB-INF/static/views/dashboard.jsp")).thenReturn(dispatcher);
		ModelAndView view = servlet.emptyGet(request);
		PageListDTO page = (PageListDTO) view.getModel().get("page");
		PageSettings settings = (PageSettings) session.getAttribute("pageSettings");
		assertNotNull(page);
		assertEquals("",page.getSearch());
		assertEquals(1,page.getPage());
		assertEquals(10,page.getSize()); 
		assertEquals("computer.name",settings.getFilter());
		assertEquals("asc",settings.getOrder());
	}
	
	@Test
	public void testWithPage() throws IOException, ServletException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);  
	    
	    Mockito.when(request.getSession()).thenReturn(session);
	    Mockito.when(request.getRequestDispatcher("/WEB-INF/static/views/dashboard.jsp")).thenReturn(dispatcher);
		session.setAttribute("pageSettings",new PageSettings());
	    PageListDTO entry = new PageListDTO();
		entry.setPage(1);
		entry.setSearch("apple");
		entry.setSize(50);
	    ModelAndView view = servlet.withPage(request,entry);
		PageListDTO page = (PageListDTO) view.getModel().get("page");
		assertNotNull(page);
		assertEquals("apple",page.getSearch());
		assertEquals(1,page.getPage());
		assertEquals(50,page.getSize());
	}
	

	@Test
	public void testWithFilter() throws IOException, ServletException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);  
	    
	    Mockito.when(request.getSession()).thenReturn(session);
	    Mockito.when(request.getRequestDispatcher("/WEB-INF/static/views/dashboard.jsp")).thenReturn(dispatcher);
		PageSettings settings = new PageSettings();
	    session.setAttribute("pageSettings",settings);
	    PageListDTO entry = new PageListDTO();
	    servlet.withFilter(request,entry,"Filter by company");
	    
	    assertEquals("company.name",settings.getFilter());

	}
	

	@Test
	public void testWithOrder() throws IOException, ServletException
	{
		HttpServletRequest request = mock(HttpServletRequest.class);  
	    
	    Mockito.when(request.getSession()).thenReturn(session);
	    Mockito.when(request.getRequestDispatcher("/WEB-INF/static/views/dashboard.jsp")).thenReturn(dispatcher);
		PageSettings settings = new PageSettings();
	    session.setAttribute("pageSettings",settings);
	    PageListDTO entry = new PageListDTO();
	    servlet.withOrder(request,entry,"Descending");
	    
	    assertEquals("desc",settings.getOrder());

	}

}*/
