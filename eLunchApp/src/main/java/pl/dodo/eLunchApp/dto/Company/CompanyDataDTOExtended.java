package pl.dodo.eLunchApp.dto.Company;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Address.AddressDTOExtended;

@Data
public class CompanyDataDTOExtended {

	@NotNull
	private CompanyDataDTOBasic companyDataDTOBasic;

	@Embedded
	@NotNull
	private AddressDTOExtended addressDTOExtended;

	@NotNull
	private String NIP;

	@NotNull
	private String regon;

	@NotNull
	private String phone;

	@NotNull
	private String email;
}
