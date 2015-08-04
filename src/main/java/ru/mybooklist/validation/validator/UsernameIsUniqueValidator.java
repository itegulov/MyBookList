package ru.mybooklist.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mybooklist.service.UserService;
import ru.mybooklist.validation.UsernameIsUnique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Daniyar Itegulov
 */
@Component
public class UsernameIsUniqueValidator implements ConstraintValidator<UsernameIsUnique, Object> {
    @Autowired
    private UserService userService;

    @Override
    public void initialize(UsernameIsUnique constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return userService.isUsernameAvailable(value.toString());
    }
}
