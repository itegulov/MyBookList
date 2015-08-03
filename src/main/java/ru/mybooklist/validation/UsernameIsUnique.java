package ru.mybooklist.validation;

import ru.mybooklist.validation.validator.UsernameIsUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Daniyar Itegulov
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameIsUniqueValidator.class)
public @interface UsernameIsUnique {
    String message() default "{Username.user.exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
