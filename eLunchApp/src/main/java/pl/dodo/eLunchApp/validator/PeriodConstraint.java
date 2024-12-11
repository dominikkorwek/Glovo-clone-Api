package pl.dodo.eLunchApp.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeriodConstranitValidator.class)
@Documented
public @interface PeriodConstraint {
    String message() default "{pl.dodo.eLunchApp.validator.PeriodConstraint}";
    Class<?>[] group() default {};
    Class<? extends Payload>[] payload() default {};
}
