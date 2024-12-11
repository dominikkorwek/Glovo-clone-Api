package pl.dodo.eLunchApp.dto.Address;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Embeddable
@Data
public class AddressDTOBasic {

	@NotNull
	private String street;

	@NotNull
	private String streetNumber;

	@NotNull
	private String localNumber;

	@NotNull
	private String city;
}

