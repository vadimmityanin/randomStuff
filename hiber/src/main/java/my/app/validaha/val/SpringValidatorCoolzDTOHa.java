package my.app.validaha.val;

import my.app.validaha.CoolDtoha;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SpringValidatorCoolzDTOHa implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CoolDtoha.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name","huinia.eto");
    }
}
