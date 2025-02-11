package pl.dodo.eLunchApp.dto.Employee;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.PersonalData.PersonalDataDTOExtended;

@Data
public class EmployeeDTOBasic {
	@NotNull
	@Embedded
	private PersonalDataDTOExtended personalData;

	@NotNull
	private EmployeeDTOId employeeDTOId;
}
