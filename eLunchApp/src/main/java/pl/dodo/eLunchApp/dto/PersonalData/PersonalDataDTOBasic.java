package pl.dodo.eLunchApp.dto.PersonalData;

import jakarta.persistence.Embeddable;
import lombok.Data;

import javax.annotation.Nullable;

@Data
@Embeddable
public class PersonalDataDTOBasic {
	@Nullable
	private String name;

	@Nullable
	private String surname;
}

