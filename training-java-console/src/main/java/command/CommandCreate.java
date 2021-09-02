package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;

import builder.ComputerBuilder;
import dto.CompanyDTO;
import model.*;
import validation.Validator;

/**
 * Class CommandCreate :
 * Create a computer
 * @author Lisa
 */
public class CommandCreate extends Command {
	private final String name = "create";
	
	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void exec(CommandHandler handler, String...args) {
		ComputerBuilder newone = new ComputerBuilder();
		for (int i = 1; i+1 < args.length;i+=2)
		{
			switch (args[i])
			{
			case ("name"):
				newone.setName(args[i+1]);
				break;
			case("introduced"):
				newone.setIntroduced(LocalDate.parse(args[i+1],DateTimeFormatter.ISO_LOCAL_DATE));
				break;
			case("discontinued"):
				newone.setDiscontinued(LocalDate.parse(args[i+1],DateTimeFormatter.ISO_LOCAL_DATE));
				break;
			case("company"):
				try {
					int cid = Integer.valueOf(args[i+1]);
					newone.setCompany(this.companyMapper.mapToCompany(this.client.target(APP_URI).path("companies").queryParam("id", cid)
							.request(MediaType.APPLICATION_JSON).get(CompanyDTO.class)));
				}
				catch (NumberFormatException e)
				{
					newone.setCompany(this.companyMapper.mapToCompany(this.client.target(APP_URI).path("companies").queryParam("name", args[i+1])
							.request(MediaType.APPLICATION_JSON).get(CompanyDTO.class)));
				}
				
				break;
			}
		}
		
		Computer newc = newone.build();
		//Create the computer in the database and locally
		Validator.validate(newc);
		
		this.client.target(APP_URI).path("computers").request(MediaType.APPLICATION_JSON).post(Entity.entity(computerMapper.mapToDTO(newc), MediaType.APPLICATION_JSON));
		//computerRequestHandler.createComputer(newc,company_id);
		this.logger.info("Computer created.");
		this.logger.info(newc.toString());
		System.out.println("Done.\n");
		
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
