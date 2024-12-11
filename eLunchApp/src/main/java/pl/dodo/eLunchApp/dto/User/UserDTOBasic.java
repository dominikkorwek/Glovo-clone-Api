package pl.dodo.eLunchApp.dto.User;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.dto.PersonalData.PersonalDataDTOExtended;

import javax.annotation.Nullable;
import java.util.List;

@Data
public class UserDTOBasic {
	@NotNull
	@Embedded
	private PersonalDataDTOExtended personalData;

	@Nullable
	private List<DeliveryAddressDTOExtended> addresses;

	@NotNull
	private UserDTOId userDTOId;
}
