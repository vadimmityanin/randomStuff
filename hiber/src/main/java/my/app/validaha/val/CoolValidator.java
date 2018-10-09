package my.app.validaha.val;

import my.app.validaha.CoolDtoha;

import javax.validation.ConstraintValidatorContext;

public class CoolValidator extends AbstractValidator<CoolzDTOHA, CoolDtoha> {

//    @Autowired
//    private SpringValidatorCoolzDTOHa validatorCoolzDTOHa;
//    @Autowired
//    private BindingResult bindingResult;


    @Override
    public boolean isValid(CoolDtoha value, ConstraintValidatorContext ctx) {
        addConstraintViolation("{vados.top}", ctx);
        addConstraintViolation("{vados.imba}", ctx);
        return false;
    }
}
