package pl.dodo.eLunchApp.dto.User;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDTOId {

	@NotNull
	private UUID uuid;
}

