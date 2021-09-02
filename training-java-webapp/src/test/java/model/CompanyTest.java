package model;

import junit.framework.TestCase;

public class CompanyTest extends TestCase{

	public void testCreate()
	{
		Company company = new Company(1,"test");
		assertEquals(1,company.getId());
		assertEquals("test",company.getName());
	}
	
	public void testId()
	{
		Company company = new Company("test");
		company.setId(1);
		assertEquals(1,company.getId());
	}
}
