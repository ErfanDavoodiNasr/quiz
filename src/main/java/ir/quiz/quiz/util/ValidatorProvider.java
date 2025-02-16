package ir.quiz.quiz.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class ValidatorProvider {

    private static volatile Validator validator;

    private static void initValidator() {
        if (validator == null) synchronized (ValidatorProvider.class) {
            if (validator == null) {
                validator = Validation.buildDefaultValidatorFactory().getValidator();
            }
        }
    }

    public static <T> Optional<List<String>> validate(T t) {
        initValidator();
        List<String> constraint = new ArrayList<>();
        for (ConstraintViolation<T> violation : validator.validate(t)) {
            constraint.add(violation.getMessage());
        }
        return constraint.isEmpty() ? Optional.empty() : Optional.of(constraint);
    }
}