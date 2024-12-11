package pl.dodo.eLunchApp.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriodTimeConstranitValidator.class)
@Documented
public @interface PeriodTimeConstraint {
    String message() default "{pl.dodo.eLunchApp.validator.PeriodTimeConstraint}";
    Class<?>[] group() default {};
    Class<? extends Payload>[] payload() default {};
}
