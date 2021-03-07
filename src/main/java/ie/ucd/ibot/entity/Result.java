package ie.ucd.ibot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class Result {
    private List<String> messages;
    private boolean isSuccess;

    public Result(List<String> messages, boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.messages = messages;
    }
    public Result(Set<? extends ConstraintViolation<?>> violations, boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.messages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
}
