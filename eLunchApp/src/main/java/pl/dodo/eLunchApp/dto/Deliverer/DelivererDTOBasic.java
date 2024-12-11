package pl.dodo.eLunchApp.dto.Deliverer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;

@Data
public class DelivererDTOBasic {
    @NotNull
    private EmployeeDTOBasic employeeDTOBasic;
}
