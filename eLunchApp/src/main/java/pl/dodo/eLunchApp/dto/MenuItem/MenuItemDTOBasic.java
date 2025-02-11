package pl.dodo.eLunchApp.dto.MenuItem;

import jakarta.validation.constraints.*;
import lombok.Data;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.UUID;

@Data
public class MenuItemDTOBasic {
	@NotNull
	@Null(groups = GroupsValidator.NewObjectValid.class)
	private UUID uuid;

	@NotBlank
	private String name;
}

