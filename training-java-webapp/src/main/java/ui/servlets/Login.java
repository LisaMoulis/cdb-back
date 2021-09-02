package ui.servlets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Login {

	@RequestMapping(value = "/login",method= RequestMethod.GET)
	public String displayLogin()
	{
		return "login";
	}
	/*
	@RequestMapping(name = "/login",method= RequestMethod.POST)
	public String processLogin()
	{
		
		return "redirect:/computers";
	}*/
	
	@RequestMapping(value = "/logout_process",method= RequestMethod.GET)
	public String processLogout(HttpServletResponse response)
	{
		response.setHeader("Status","401 Logout");
		response.setHeader("WWW-Authenticate","Invalidate, Basic realm=logout");
		return "redirect:/logout_end";
	}
	
	@RequestMapping(value = "/logout_end",method= RequestMethod.GET)
	public String processLogoutEnd(HttpServletResponse response)
	{
		response.setHeader("Status","401 Logout");
		response.setHeader("WWW-Authenticate","Invalidate, Basic realm=logout");
		return "computers";
	}
}
