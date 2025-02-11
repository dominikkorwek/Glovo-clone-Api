package pl.dodo.eLunchApp.dto.Dish;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.UUID;

@Data
public class DishDTOId {

	@NotNull
	@Null(groups = GroupsValidator.NewObjectValid.class)
	private UUID uuid;
}

