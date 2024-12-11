package pl.dodo.eLunchApp.dto.Period;

import jakarta.persistence.Embeddable;
import lombok.Data;
import pl.dodo.eLunchApp.validator.PeriodTimeConstraint;

import javax.annotation.Nullable;
import java.time.LocalTime;

@PeriodTimeConstraint
@Embeddable
@Data
public class PeriodTimeDTOBasic {
	@Nullable
	private LocalTime begin;

	@Nullable
	private LocalTime end;
}
