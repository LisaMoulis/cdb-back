package ui.servlets;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorPages {

	@Autowired
	private ResourceBundleMessageSource messages;
	
	@RequestMapping("/401")
	public ModelAndView page401(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ModelAndView page = new ModelAndView("error");
		page.addObject("message",messages.getMessage("error401",new Object[] {},Locale.getDefault()));
		return page;
	}
	
	@RequestMapping("/403")
	public ModelAndView page403(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ModelAndView page = new ModelAndView("error");
		page.addObject("message",messages.getMessage("error403",new Object[] {},Locale.getDefault()));
		return page;
	}
	
	@RequestMapping("/404")
	public ModelAndView page404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ModelAndView page = new ModelAndView("error");
		page.addObject("message",messages.getMessage("error404",new Object[] {},Locale.getDefault()));
		return page;
	}
	
	@RequestMapping("/500")
	public ModelAndView page500(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ModelAndView page = new ModelAndView("error");
		page.addObject("message",messages.getMessage("error500",new Object[] {},Locale.getDefault()));
		return page;
	}
}
