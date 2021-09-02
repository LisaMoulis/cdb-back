package model.exceptions;

public class IncorrectDateOrderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncorrectDateOrderException()
	{
		super("The discontinued date cannot be before the introduced one!");
	}

}
