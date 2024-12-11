package pl.dodo.eLunchApp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.dodo.eLunchApp.model.PeriodTime;

public class PeriodTimeConstranitValidator implements ConstraintValidator<PeriodConstraint, PeriodTime>{
    @Override
    public void initialize(PeriodConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(PeriodTime periodTime, ConstraintValidatorContext constraintValidatorContext) {
        return periodTime.getBegin() == null ||
                periodTime.getEnd() == null ||
                periodTime.getBegin().isBefore(periodTime.getEnd());
    }
}
