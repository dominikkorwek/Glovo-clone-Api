package pl.dodo.eLunchApp.dto.DeliveryAddress;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.dodo.eLunchApp.dto.Address.AddressDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOBasic;


import javax.annotation.Nullable;
import java.util.UUID;

@Data
public class DeliveryAddressDTOExtended {

	@NotNull
	private AddressDTOExtended addressDTOExtended;

	@NotNull
	private UUID uuid;

	@Nullable
	private String description;

	@NotNull
	private UserDTOBasic user;
}
