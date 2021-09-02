package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import dto.CompanyDTO;
import dto.ComputerDTO;
import model.*;

/**
 * Class CommandUpdate :
 * Modify the information of a computer
 * @author Lisa
 */
public class CommandUpdate extends Command{
	private final String name = "update";

	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void exec(CommandHandler handler, String...args) {
		Computer toupdate = null;
		try {
			int id = Integer.valueOf(args[1]);
			toupdate = computerMapper.mapToComputer(this.client.target(APP_URI).path("computers").queryParam("id", id)
					.request(MediaType.APPLICATION_JSON).get(ComputerDTO.class));
		}
		catch (NumberFormatException e)
		{
			toupdate = computerMapper.mapToComputer(this.client.target(APP_URI).path("computers").queryParam("name", args[1])
					.request(MediaType.APPLICATION_JSON).get(ComputerDTO.class));
		}
		if (toupdate != null)
		{
			for (int i = 2; i+1 < args.length;i+=2)
			{
				switch (args[i])
				{
				case ("name"):
					toupdate.setName(args[i+1]);
					break;
				case("introduced"):
					toupdate.setIntroduced(LocalDate.parse(args[i+1],DateTimeFormatter.ISO_LOCAL_DATE));
					break;
				case("discontinued"):
					toupdate.setDiscontinued(LocalDate.parse(args[i+1],DateTimeFormatter.ISO_LOCAL_DATE));
					break;
				case("company"):
					try {
						int cid = Integer.valueOf(args[i+1]);
						toupdate.setCompany(this.companyMapper.mapToCompany(this.client.target(APP_URI).path("companies").queryParam("id", cid)
								.request(MediaType.APPLICATION_JSON).get(CompanyDTO.class)));
					}
					catch (NumberFormatException e)
					{
						toupdate.setCompany(this.companyMapper.mapToCompany(this.client.target(APP_URI).path("companies").queryParam("name", args[i+1])
								.request(MediaType.APPLICATION_JSON).get(CompanyDTO.class)));
					}
					
					break;
				}
								
				//Update the computer in the database
				this.client.target(APP_URI).path("computers/update").request(MediaType.APPLICATION_JSON).post(Entity.entity(computerMapper.mapToDTO(toupdate), MediaType.APPLICATION_JSON));
				//computerRequestHandler.updateComputer(toupdate,company_id);
			}
			this.client.target(APP_URI).path("computers/update").request(MediaType.APPLICATION_JSON).post(Entity.entity(computerMapper.mapToDTO(toupdate), MediaType.APPLICATION_JSON));
			this.logger.debug("Computer info updated.");
			
		}
		else
		{
			this.logger.error("An issue in the computer update happened.");
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
