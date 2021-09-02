package ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.*;

import junit.framework.TestCase;
/*
public class CLIReaderTest extends TestCase {
	
	private static final InputStream DEFAULT_STDIN = System.in;

	@After
	public void rollbackChangesToStdin() {
	    System.setIn(DEFAULT_STDIN);
	}
	
	public void testOpen()
	{
		InputStream in = new ByteArrayInputStream(new String("command test").getBytes());
	    System.setIn(in);
		assertTrue(CLIReader.canRead());
	}

	public void testRead()
	{
		
	    String[] command = CLIReader.getLine();
	    if (command.length != 2)
	    {
	    	fail("Impossible number of commands : "+command.length);
	    }
	    else
	    {
	    	assertEquals("command",command[0]);
	    	assertEquals("test",command[1]);
	    }
	}
	
	public void testClosed()
	{
		CLIReader.close();
		assertFalse(CLIReader.canRead());
		assertNull(CLIReader.getLine());
		new CLIReader();
	}
	
}*/
