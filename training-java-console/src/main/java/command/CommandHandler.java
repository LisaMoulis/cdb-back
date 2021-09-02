package command;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import model.ComputerList;


/**
 * Class CommandHandler :
 * Manage the commands and execute them
 * @author Lisa
 */

public class CommandHandler {

	private ArrayList<Command> commands;
	private ComputerList computerList = new ComputerList();
	private Client client = ClientBuilder.newClient();
	
	
	public CommandHandler()
	{
		//Create the list of commands with the basic ones
		this.commands = new ArrayList<Command>();
		this.commands.add(new CommandCreate());
		this.commands.add(new CommandDelete());
		this.commands.add(new CommandDeleteCompany());
		this.commands.add(new CommandDetails());
		this.commands.add(new CommandListCompanies());
		this.commands.add(new CommandListComputers());
		this.commands.add(new CommandUpdate());
		this.commands.add(new CommandQuit());
		this.commands.add(new CommandNextPage());
		this.commands.add(new CommandPreviousPage());
		
		this.commands.forEach(c -> {
			c.setClient(client);
		});
	}
	
	/**
	 * @param args	keyword of the entered command + arguments
	 */
	public void exec(String...args)
	{
		//Calls the exec of the specified command
		for(Command c : commands)
		{
			if (c.getName().equals(args[0]))
			{
				c.exec(this,args);
			}
		}
	}
	
	/**
	 * @param command	command to add
	 */
	public void add(Command command)
	{
		if (!commands.contains(command))
		{
			commands.add(command);
		}
	}
	
	/**
	 * @param command	command to remove
	 */
	public void remove(Command command)
	{
		if (commands.contains(command))
		{
			commands.remove(command);
		}
	}
	
	public ComputerList getPage()
	{
		return this.computerList;
	}
	
}
