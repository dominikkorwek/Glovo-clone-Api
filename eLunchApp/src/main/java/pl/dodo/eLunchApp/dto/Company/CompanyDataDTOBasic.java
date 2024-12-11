package pl.dodo.eLunchApp.dto.Company;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Embeddable
@Data
public class CompanyDataDTOBasic {

	@NotNull
	private String name;
}

