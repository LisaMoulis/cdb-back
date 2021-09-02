package command;

import java.util.List;
import javax.ws.rs.core.MediaType;

import model.ComputerList;

/**
 * Class CommandListComputers :
 * Display a list of the computers
 * @author Lisa
 */
public class CommandListComputers extends Command{
	private final String name = "computers";

	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void exec(CommandHandler handler, String...args) {
		ComputerList page = handler.getPage();
		if (args.length == 1)
		{
			page.setPage(1);
			this.logger.debug("Displaying the list of computers.");
			String str = "List of the computers :\n";
			this.logger.debug(client.target(APP_URI).path("computers").queryParam("page", 1).queryParam("size", 10).getUri().toString());
			str += client.target(APP_URI).path("computers").queryParam("page", 1).queryParam("size", 10)
					.request(MediaType.APPLICATION_JSON).get(List.class);
			System.out.println(str);
			//System.out.println("List of the computers :\n" + pageService.getPage(page,"","computer.id","asc"));
		}
		else if (args.length == 2)
		{
			this.logger.debug("Displaying the list of computers.");
			page.setPage(Integer.valueOf(args[2]));
			String str = "List of the computers :\n";
			str += client.target(APP_URI).path("computers").queryParam("page", args[2]).queryParam("size", 10)
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
