package pl.dodo.eLunchApp.dto.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.UUID;

@Data
public class ProductDTOBasic {
	@NotNull
	@Null(groups = GroupsValidator.NewObjectValid.class)
	private UUID uuid;

	@NotBlank
	private String name;
}

