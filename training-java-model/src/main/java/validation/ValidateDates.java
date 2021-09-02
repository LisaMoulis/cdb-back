package validation;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import javax.validation.*;

@Target( { TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = Validator.class)
@Documented
public @interface ValidateDates {

	String message() default "Check if is valid";
	Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}