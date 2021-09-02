package command;

import ui.CLIReader;

/**
 * Class CommandQuit :
 * Close the program
 * @author Lisa
 */
public class CommandQuit extends Command {

	private final String name = "quit";

	public String getName()
	{
		return this.name;
	}
	
	@Override
	public void exec(CommandHandler handler, String...args) {
		CLIReader.close();
		System.out.println("Bye!");
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
