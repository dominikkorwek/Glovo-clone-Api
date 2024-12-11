package pl.dodo.eLunchApp.dto.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductDTOBasic {
	@NotNull
	private UUID uuid;

	@NotBlank
	private String name;
}

