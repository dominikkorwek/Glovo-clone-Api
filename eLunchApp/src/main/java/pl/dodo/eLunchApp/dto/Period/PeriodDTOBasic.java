package pl.dodo.eLunchApp.dto.Period;

import jakarta.persistence.Embeddable;
import lombok.Data;
import pl.dodo.eLunchApp.validator.PeriodConstraint;


import javax.annotation.Nullable;
import java.time.LocalDateTime;

@PeriodConstraint
@Embeddable
@Data
public class PeriodDTOBasic {
	@Nullable
	private LocalDateTime begin;

	@Nullable
	private LocalDateTime end;
}
