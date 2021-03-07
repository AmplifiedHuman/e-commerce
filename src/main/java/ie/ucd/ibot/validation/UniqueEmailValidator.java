package ie.ucd.ibot.validation;

import ie.ucd.ibot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserService userService;

    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && !userService.isEmailInUse(email);
    }
}
