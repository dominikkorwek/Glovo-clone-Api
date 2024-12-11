package pl.dodo.eLunchApp.dto.Discount;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Period.PeriodDTOBasic;

import java.util.UUID;

@Data
public class DiscountCodeDTOBasic {

	@NotNull
	private UUID uuid;

	@NotBlank
	private String code;

	@NotNull
	@Embedded
	private PeriodDTOBasic period;
}
