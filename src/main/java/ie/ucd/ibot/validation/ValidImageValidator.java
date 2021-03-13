package ie.ucd.ibot.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidImageValidator implements ConstraintValidator<ValidImage, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif", "image/svg");
        return multipartFile.isEmpty() || (multipartFile.getSize() < 8000000
                && contentTypes.contains(multipartFile.getContentType()));
    }
}
