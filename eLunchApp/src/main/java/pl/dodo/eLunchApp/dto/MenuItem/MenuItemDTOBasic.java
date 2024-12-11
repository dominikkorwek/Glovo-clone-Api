package pl.dodo.eLunchApp.dto.MenuItem;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class MenuItemDTOBasic {
	@NotNull
	private UUID uuid;

	@NotBlank
	private String name;
}

