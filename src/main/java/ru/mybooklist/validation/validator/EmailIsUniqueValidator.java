package ru.mybooklist.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mybooklist.service.UserService;
import ru.mybooklist.validation.EmailIsUnique;
import ru.mybooklist.validation.UsernameIsUnique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Daniyar Itegulov
 */
@Component
public class EmailIsUniqueValidator implements ConstraintValidator<EmailIsUnique, Object> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(EmailIsUnique constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return userService.isEmailAvailable(value.toString());
    }
}
