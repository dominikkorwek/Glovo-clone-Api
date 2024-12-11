package pl.dodo.eLunchApp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.dodo.eLunchApp.model.Period;

public class PeriodConstranitValidator implements ConstraintValidator<PeriodConstraint, Period> {
    @Override
    public void initialize(PeriodConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Period period, ConstraintValidatorContext constraintValidatorContext) {
        return period.getBegin() == null ||
                period.getEnd() == null ||
                period.getBegin().isBefore(period.getEnd());
    }
}
