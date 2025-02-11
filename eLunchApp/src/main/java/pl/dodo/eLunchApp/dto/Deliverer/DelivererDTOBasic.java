package pl.dodo.eLunchApp.dto.Deliverer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;
import pl.dodo.eLunchApp.validator.GroupsValidator;

@Data
public class DelivererDTOBasic {
    @NotNull @Null(groups = GroupsValidator.DelivererExtendedValid.class)
    private EmployeeDTOBasic employeeDTOBasic;
}
