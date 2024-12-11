package pl.dodo.eLunchApp.dto.Dish;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class DishDTOId {

	@NotNull
	private UUID uuid;
}

