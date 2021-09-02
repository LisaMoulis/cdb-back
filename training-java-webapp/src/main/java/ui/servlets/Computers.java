package ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dto.PageListDTO;
import dto.PageSettings;
import mapper.ComputerDTOMapper;
import model.ComputerList;
import service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/computers")
public class Computers {
	
	private final Logger logger = LoggerFactory.getLogger(AddComputer.class);

	private PageService pageService;
	
	private ComputerDTOMapper computerDTOMapper;
	
	@Autowired
	public void setPageService(PageService pageService)
	{
		this.pageService = pageService;
	}
	
	@Autowired
	public void setComputerDTOMapper(ComputerDTOMapper computerDTOMapper)
	{
		this.computerDTOMapper = computerDTOMapper;
	}
	
	private void setPageDTO(PageListDTO page, PageSettings settings)
	{
		page.setNbComputers(pageService.getNbComputers(page.getSearch()));
		if (page.getPage() < 1)
		{
			page.setPage(1);
		}
		else if (page.getPage() > page.getNbPages())
		{
			page.setPage(page.getNbPages());
		}
		ComputerList list = new ComputerList(page.getPage(),page.getSize());
		pageService.getPage(list, page.getSearch(), settings.getFilter(),settings.getOrder());
		page.setComputers(computerDTOMapper.mapToDTOList(list.getComputers()));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView emptyGet(HttpServletRequest request) throws ServletException,IOException 
	{	
		ModelAndView view = new ModelAndView("/dashboard");
		PageSettings settings = new PageSettings();
		PageListDTO page = new PageListDTO();
		request.getSession().setAttribute("pageSettings", settings);
		logger.debug("Default settings applying : page "+ page.getPage() + ", size " + page.getSize());
		
		this.setPageDTO(page,settings);
		view.addObject("page",page);
		return view;
	}

	@RequestMapping(params = {"page","size","search"})
	public ModelAndView withPage(HttpServletRequest request, @Valid PageListDTO page)
	{
		ModelAndView view = new ModelAndView("/dashboard");
		PageSettings settings = (PageSettings) request.getSession().getAttribute("pageSettings");
		this.setPageDTO(page,settings);
		view.addObject("page",page);
		return view;
	}
	
	@RequestMapping(params = {"page","size","search","searchorder"})
	public ModelAndView withOrder(HttpServletRequest request, @Valid PageListDTO page,@RequestParam("searchorder") String order)
	{
		PageSettings settings = (PageSettings) request.getSession().getAttribute("pageSettings");
		if (order.equals("Descending"))
		{
			settings.setOrder("desc");
		}
		else
		{
			settings.setOrder("asc");
		}
		return this.withPage(request, page);
	}
	
	@RequestMapping(params = {"page","size","search","searchsubmit"})
	public ModelAndView withFilter(HttpServletRequest request, @Valid PageListDTO page, @RequestParam("searchsubmit") String filter)
	{
		PageSettings settings = (PageSettings) request.getSession().getAttribute("pageSettings");
		
		if (filter.equals("Filter by company"))
		{
			settings.setFilter("company.name");
		}
		else if (filter.equals("Filter by name"))
		{
			settings.setFilter("computer.name");
		}
		else if (filter.equals("Cancel"))
		{
			page.setSearch("");
		}
		return this.withPage(request, page);
	}
}
