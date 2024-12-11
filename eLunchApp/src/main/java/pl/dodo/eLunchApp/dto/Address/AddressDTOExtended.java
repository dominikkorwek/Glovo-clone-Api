package pl.dodo.eLunchApp.dto.Address;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import javax.annotation.Nullable;

@Data
public class AddressDTOExtended {

	@NotNull
	private AddressDTOBasic addressDTOBasic;

	@Nullable
	private String borough;

	@Nullable
	private String county;

	@Nullable
	private String state;
}
