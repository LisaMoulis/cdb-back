package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import model.Company;

/**
 * Class CompanyMapper :
 * Map the information between the database and companies
 * @author Lisa
 */

@Component
public class CompanyDAOMapper implements RowMapper<Company> {

	/**
	 * @param result	Representation of the company from the database
	 * @return Name of the company
	 * @throws SQLException
	 */
	public Company mapRow(ResultSet result, int rowNum) throws SQLException
	{
		return new Company(result.getInt("id"),result.getString("name"));
	}
}
