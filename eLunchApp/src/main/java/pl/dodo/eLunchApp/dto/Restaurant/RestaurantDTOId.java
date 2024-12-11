package pl.dodo.eLunchApp.dto.Restaurant;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantDTOId {
	@NotNull
	private UUID uuid;
}

