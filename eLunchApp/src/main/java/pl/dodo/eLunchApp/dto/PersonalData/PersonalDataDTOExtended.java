package pl.dodo.eLunchApp.dto.PersonalData;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.enums.Sex;

import javax.annotation.Nullable;

@Data
public class PersonalDataDTOExtended {
	@Nullable
	private Sex sex;

	@Nullable
	private String phone;

	@Nullable
	private String email;

	@NotNull
	private PersonalDataDTOBasic personalDataDTOBasic;
}
