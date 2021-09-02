package builder;

import dto.ComputerDTO;
import junit.framework.TestCase;

public class ComputerDTOBuilderTest extends TestCase{

	public void testCreateEmpty() throws  Exception
	{
		ComputerDTOBuilder builder = new ComputerDTOBuilder();
		assertEquals(new ComputerDTO(-1,"","","","",-1),builder.build());
	}
	
	public void testCreateMinimum() throws Exception
	{
		ComputerDTOBuilder builder = new ComputerDTOBuilder();
		builder.setName("test");
		ComputerDTO dto = builder.build();
		assertEquals("test",dto.getName());
	}
	
	public void testCreateFull() throws Exception
	{
		ComputerDTOBuilder builder = new ComputerDTOBuilder();
		builder.setId(1).setName("test").setIntroduced("2020-01-01").setDiscontinued("2020-02-02").setCompany("One company").setCompanyId(5);
		ComputerDTO testc = new ComputerDTO(1,"test","2020-01-01","2020-02-02","One company",5);
		assertEquals(testc,builder.build());
	}
}
