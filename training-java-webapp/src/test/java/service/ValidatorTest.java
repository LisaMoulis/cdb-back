package service;

import java.time.LocalDate;

import builder.ComputerBuilder;
import junit.framework.TestCase;
import model.Computer;
import validation.Validator;

public class ValidatorTest extends TestCase {

	public void testEmptyName()
	{
		try 
		{
			Computer c = new Computer("test");
			c.setName(null);
			Validator.validate(c);
			fail();
		}
		catch(RuntimeException e)
		{}
	}
	
	public void testEmptyIntroduced()
	{
		try 
		{
			Computer c = new ComputerBuilder().setName("test").setDiscontinued(LocalDate.of(2020, 1, 1)).build();
			Validator.validate(c);
			fail("Issue "+c.getDiscontinued() + " and " + c.getIntroduced());
		}
		catch(RuntimeException e)
		{}
	}
	
	public void testInvalidDateOrder()
	{
		try 
		{
			Computer c = new ComputerBuilder().setName("test").setIntroduced(LocalDate.of(2021, 1, 1)).setDiscontinued(LocalDate.of(2020, 1, 1)).build();
			Validator.validate(c);
			fail();
		}
		catch(RuntimeException e)
		{}
	}
	
}
