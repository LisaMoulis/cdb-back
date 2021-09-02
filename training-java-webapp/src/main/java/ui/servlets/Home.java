package ui.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Controller
public class Home extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WebApplicationContext springContext;
	
	@Override
	public void init(final ServletConfig config) throws ServletException {
		super.init(config);
		springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
		beanFactory.autowireBean(this);
	}
	
	@Override
	public String getServletInfo() {
		return "My home page";
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		out.println( "<html>" );
		out.println( "<head>");
		out.println( "<title>Home</title>" );
		out.println( "</head>" );
		out.println( "<body>" );
		out.println( "<h1>Home</h1><p>Home, sweet home</p>" );
		out.println( "</body>" );
		out.println( "</html>" );
		out.close();
		//request.getRequestDispatcher("/static/views/dashboard.jsp").forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException,IOException {
			  this.doGet(request, response);
			}

}
