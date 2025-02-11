package pl.dodo.eLunchApp.dto.Discount;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Period.PeriodDTOBasic;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.UUID;

@Data
public class DiscountCodeDTOBasic {

	@NotNull
	@Null(groups = GroupsValidator.NewObjectValid.class)
	private UUID uuid;

	@NotBlank
	private String code;

	@NotNull
	@Embedded
	private PeriodDTOBasic period;
}
