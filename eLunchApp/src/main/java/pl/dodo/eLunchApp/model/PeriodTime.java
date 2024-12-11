package pl.dodo.eLunchApp.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import lombok.Data;
import pl.dodo.eLunchApp.validator.PeriodTimeConstraint;

import java.time.LocalTime;

@Embeddable
@Data
@PeriodTimeConstraint
public class PeriodTime {

    @Nullable
    private LocalTime begin;

    @Nullable
    private LocalTime end;
}
