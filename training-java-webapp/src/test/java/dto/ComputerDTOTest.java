package dto;


import builder.ComputerDTOBuilder;
import junit.framework.TestCase;

public class ComputerDTOTest extends TestCase {
	
	public void testEmptyRequest()
	{
		ComputerDTO dto = new ComputerDTOBuilder().build();
		assertEquals("",dto.getName());
		assertEquals("",dto.getIntroduced());
		assertEquals("",dto.getDiscontinued());
		assertEquals("",dto.getCompany());
		assertEquals(-1,dto.getId());
		assertEquals(-1,dto.getCompanyId());
	}
	
	public void testFullRequest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		ComputerDTO dto = new ComputerDTOBuilder().setName("test").setIntroduced("2021-01-01").setDiscontinued("2021-02-02").setCompany("testcompany").setCompanyId(3).build();
		assertEquals("test",dto.getName());
		assertEquals("2021-01-01",dto.getIntroduced());
		assertEquals("2021-02-02",dto.getDiscontinued());
		assertEquals("testcompany",dto.getCompany());
		assertEquals(3,dto.getCompanyId());
	}
}
