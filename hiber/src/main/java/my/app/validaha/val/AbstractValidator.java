package my.app.validaha.val;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public abstract class AbstractValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

    @Override
    public void initialize(A constraintAnnotation) {

    }

    protected void addConstraintViolation(String messageTemplate, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation();
    }

}
