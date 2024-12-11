package pl.dodo.eLunchApp.dto.OpenTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class OpenTimeDTOBasic {

	@NotNull
	private UUID uuid;
}

