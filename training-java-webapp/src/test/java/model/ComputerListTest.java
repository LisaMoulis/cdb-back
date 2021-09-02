package model;

import junit.framework.TestCase;

public class ComputerListTest extends TestCase {

	private ComputerList list = new ComputerList();
	
	public void testDefault()
	{
		assertEquals(1,list.getPage());
		assertEquals(10,list.getSize());
	}
	
	public void testPage()
	{
		list.setPage(2);
		assertEquals(2,list.getPage());
		list.setPage(1);
	}
	
	public void testSize()
	{
		list.setSize(20);
		assertEquals(20,list.getSize());
		list.setPage(20);
	}
}
