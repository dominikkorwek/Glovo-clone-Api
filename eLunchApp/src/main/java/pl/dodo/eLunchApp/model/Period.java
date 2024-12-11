package pl.dodo.eLunchApp.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import lombok.Data;
import pl.dodo.eLunchApp.validator.PeriodConstraint;

import java.time.LocalDateTime;

@Embeddable
@Data
@PeriodConstraint
public class Period {

    @Nullable
    private LocalDateTime begin;

    @Nullable
    private LocalDateTime end;
}
