package pl.dodo.eLunchApp.dto.Ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class IngredientDTOBasic {
	@NotNull
	private UUID uuid;

	@NotBlank
	private String name;

	@NotNull
	private Boolean isAllergen;
}
