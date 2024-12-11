package pl.dodo.eLunchApp.dto.Restaurant;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.LoginData.LoginDataDTOBasic;

@Data
public class RestaurantDTOBasic {
	@NotBlank
	private String name;

	@NotNull
	@Embedded
	private LoginDataDTOBasic loginDataDTOBasic;

	@NotNull
	private RestaurantDTOId restaurantDTOId;
}
