package pl.dodo.eLunchApp.dto.OpenTime;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Period.PeriodTimeDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOId;
import pl.dodo.eLunchApp.enums.DayOfWeek;

@Data
public class OpenTimeDTOExtended {
	@NotNull
	private DayOfWeek dayOfWeek;

	@NotNull
	@Embedded
	private PeriodTimeDTOBasic periodTimeDTOBasic;

	@NotNull
	private RestaurantDTOId restaurantDTO;

	@NotNull
	private OpenTimeDTOBasic openTimeDTOBasic;
}
