package command;

import javax.ws.rs.core.MediaType;

import dto.ComputerDTO;

/**
 * Class CommandDetails :
 * Display the details of a computer
 * @author Lisa
 */
public class CommandDetails extends Command {
	private final String name = "details";

	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void exec(CommandHandler handler, String...args) {
		if (args.length == 2)
		{
			this.logger.debug("Details of the computer displayed.");
			String str = "Details of the computer :\n";
			try {
				int id = Integer.valueOf(args[1]);
				str += this.client.target(APP_URI).path("computers").queryParam("id", id)
						.request(MediaType.APPLICATION_JSON).get(ComputerDTO.class);//computerRequestHandler.getComputer(id);
			}
			catch (NumberFormatException e)
			{
				str += this.client.target(APP_URI).path("computers").queryParam("name", args[1])
						.request(MediaType.APPLICATION_JSON).get(ComputerDTO.class);//computerService.getComputer(args[1]);
			}
			System.out.println(str);
			logger.debug("Details of the computer displayed.");
		}
		else
		{
			logger.debug("Mismatch of number of arguments.");
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
