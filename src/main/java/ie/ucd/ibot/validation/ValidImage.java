package ie.ucd.ibot.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidImageValidator.class})
public @interface ValidImage {
    String message() default "The provided image is not valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}