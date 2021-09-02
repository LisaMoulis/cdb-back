package command;

import javax.ws.rs.core.MediaType;

import dto.CompanyDTO;

/**
 * Class CommandDelete :
 * Delete a computer
 * @author Lisa
 */
public class CommandDeleteCompany extends Command {

	private final String name = "delete-company";

	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void exec(CommandHandler handler, String...args) {
		if (args.length == 2)
		{
			try {
				int id = Integer.valueOf(args[1]);
				this.client.target(APP_URI).path("companies").queryParam("id", id)
					.request(MediaType.APPLICATION_JSON).delete(CompanyDTO.class);
				//companyService.removeCompany(id);
			}
			catch (NumberFormatException e)
			{
				this.client.target(APP_URI).path("companies").queryParam("name", args[1])
					.request(MediaType.APPLICATION_JSON).delete(CompanyDTO.class);
				//companyService.removeCompany(args[1]);
			}
			this.logger.debug("Company deleted.");
		}
		else
		{
			this.logger.error("Mismatch of number of arguments.");
			System.out.println("Mismatch of number of arguments\n");
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
