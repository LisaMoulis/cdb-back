package command;

import javax.ws.rs.client.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mapper.CompanyDTOMapper;
import mapper.ComputerDTOMapper;

/**
 * Class Command :
 * Represent an executable command
 * @author Lisa
 */

public abstract class Command {

	protected final Logger logger = LoggerFactory.getLogger(Command.class);
	protected ComputerDTOMapper computerMapper = new ComputerDTOMapper();
	protected CompanyDTOMapper companyMapper = new CompanyDTOMapper();
	protected Client client;
	protected static final String APP_URI = "http://localhost:8080/training-java-webapp/service";
	
	public void setClient(Client client)
	{
		this.client = client;
	}
	
	/**
	 * @param args	keyword of the command + arguments
	 */
	public abstract void exec(CommandHandler handler,String...args);
	
	public abstract String getName();
	
	@Override
	public abstract boolean equals(Object object);

	
}
