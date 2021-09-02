package model.exceptions;

public class EmptyNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyNameException()
	{
		super("The name cannot be empty!");
	}

}
