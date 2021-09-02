package dto;

import org.mockito.Spy;

import junit.framework.TestCase;

public class PageListDTOTest extends TestCase {

	@Spy
	private PageListDTO page = new PageListDTO();
	
	public void testDefault()
	{
		page = new PageListDTO();
		assertEquals(1,page.getPage());
		assertEquals(10,page.getSize());
		assertEquals("",page.getSearch());
	}
		
	public void testSearch()
	{
		page.setSearch("something");
		assertEquals("something",page.getSearch());
	}
	
	public void testSize()
	{
		page.setSize(20);
		assertEquals(20,page.getSize());
	}
	
	public void testPage()
	{
		page.setPage(1);
		assertEquals(1,page.getPage());
	}
}
