package ui.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ComputerService;

@Controller
@RequestMapping("/delete")
public class Delete {

	private final Logger logger = LoggerFactory.getLogger(Delete.class);
	@Autowired
	private ComputerService computerService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(HttpServletResponse response,@RequestParam(name="selection") String select) throws ServletException,IOException 
	{
		logger.debug("Computer info retrieved. Trying to delete the computers.");
		String[] selection = select.split(",");
		try
		{
			computerService.removeSelectedComputer(selection);
			logger.info("Computers deleted.");
		}
		catch (RuntimeException e)
		{
			PrintWriter out = response.getWriter();
			logger.debug("A computer deletion failed. Error message : "+e.getMessage());
			out.println("<script>alert(\""+ e.getMessage()+"\")</script>");
		}
		return "redirect:/computers";
	}
}
