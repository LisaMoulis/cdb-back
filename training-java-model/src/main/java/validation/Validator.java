package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dto.ComputerDTO;
import model.Computer;
import model.exceptions.*;

public class Validator implements ConstraintValidator<ValidateDates,ComputerDTO> {

	public static void validate(Computer computer) throws RuntimeException
	{
		if (computer.getName() == null || computer.getName().equals(""))
		{
			throw new EmptyNameException();
		}
		
		if (computer.getDiscontinued() != null)
		{
			if (computer.getIntroduced() == null)
			{
				throw new EmptyIntroducedException();
			}
			else if (computer.getIntroduced().isAfter(computer.getDiscontinued()))
			{
				throw new IncorrectDateOrderException();
			}
		}
	}

	@Override
	public boolean isValid(ComputerDTO value, ConstraintValidatorContext context) {
        if (value.getDiscontinued() == null || value.getDiscontinued().equals(""))
        {
        	return true;
        }
        else if (value.getIntroduced() == null || value.getIntroduced().equals(""))
        {
        	return false;
        }
        else
        { 
        	try 
        	{
	        	if (LocalDate.parse(value.getIntroduced(), DateTimeFormatter.ISO_LOCAL_DATE).isAfter(LocalDate.parse(value.getDiscontinued(), DateTimeFormatter.ISO_LOCAL_DATE)))
	        	{
	        		return false;
	        	}
	        	return true;
        	}
        	catch (RuntimeException e)
        	{
        		return false;
        	}
        }
	}
}
