package command;

import java.util.List;

import javax.ws.rs.core.MediaType;

import model.ComputerList;

/**
 * Class CommandNextPage :
 * Display the next page of computers
 * @author Lisa
 */
public class CommandNextPage extends Command{
	private final String name = "next";

	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void exec(CommandHandler handler, String...args) {
		if (args.length == 1)
		{
			ComputerList page = handler.getPage();
			page.setPage(page.getPage()+1);
			this.logger.debug("Next page of computers displayed.");
			String str = "List of the computers :\n";
			str += client.target(APP_URI).path("computers").queryParam("page", page.getPage()).queryParam("size", 10)
					.request(MediaType.APPLICATION_JSON).get(List.class);
			System.out.println(str);
			//System.out.println("List of the computers :\n" + pageService.getPage(page,"","computer.id","asc"));
		}
		else
		{
			logger.error("Mismatch of number of arguments.");
			System.out.println("Mismatch of number of arguments.\n");
		}
		
	}
	
	@Override
	public boolean equals(Object object)
	{
		if (object == null || !this.name.equals(((Command)object).getName()))
		{
			return false;
		}
		return true;
	}
}
