package model.exceptions;

public class RollbackHappened extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RollbackHappened()
	{
		super("Something went wrong, please submit the data again!");
	}
}
