package ui;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import command.*;

/**
 * Class Main :
 * Entry point
 * @author Lisa
 */

@Component
public class Main {

	private static CommandHandler commands;
	protected static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] str)
	{
		logger.debug("Program started.");
		commands = new CommandHandler();//context.getBean(CommandHandler.class);
		getFromCommandLine();
	}
	
	
	private static void getFromCommandLine()
	{
		//Read the command line inputs while it's possible
		while (CLIReader.canRead())
		{
			System.out.println("\nEnter your command :\n");
			commands.exec(CLIReader.getLine());
		}
		logger.debug("Program closed.");
	}
	
}

//Inversion de dépendance :  la classe est la mère, le nom est la classe fille
