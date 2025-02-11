package pl.dodo.eLunchApp.dto.DeliveryAddress;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.dodo.eLunchApp.dto.Address.AddressDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOBasic;
import pl.dodo.eLunchApp.validator.GroupsValidator;


import javax.annotation.Nullable;
import java.util.UUID;

@Data
public class DeliveryAddressDTOExtended {

	@NotNull
	private AddressDTOExtended addressDTOExtended;

	@NotNull
	@Null(groups = GroupsValidator.NewObjectValid.class)
	private UUID uuid;

	@Nullable
	private String description;

	@NotNull
	private UserDTOBasic user;
}
