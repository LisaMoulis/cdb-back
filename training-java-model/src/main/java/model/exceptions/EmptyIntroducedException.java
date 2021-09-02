package model.exceptions;

public class EmptyIntroducedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyIntroducedException()
	{
		super("Enter an introduced date if you enter a discontinued one!");
	}

}


