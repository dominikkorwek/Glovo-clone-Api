package pl.dodo.eLunchApp.dto.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.UUID;

@Data
public class UserDTOId {

	@NotNull
	@Null(groups = GroupsValidator.NewObjectValid.class)
	private UUID uuid;
}

