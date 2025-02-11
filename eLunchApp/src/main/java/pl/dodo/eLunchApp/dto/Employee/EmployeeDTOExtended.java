package pl.dodo.eLunchApp.dto.Employee;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.LoginData.LoginDataDTOBasic;
import pl.dodo.eLunchApp.enums.Archive;

@Data
public class EmployeeDTOExtended {
	@NotNull
	@Embedded
	private LoginDataDTOBasic loginData;

	@NotNull
	private Archive archive;

	@NotNull
	private EmployeeDTOBasic employeeDTOBasic;
}
