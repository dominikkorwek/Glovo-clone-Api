package pl.dodo.eLunchApp.dto.Deliverer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOExtended;
import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import javax.annotation.Nullable;
import java.util.List;


@Data
public class DelivererDTOExtended {

	@Nullable
	@Null(groups = GroupsValidator.DelivererValid.class)
	private List<OrderDTOBasic> orders;

	@NotNull
	private EmployeeDTOExtended employeeDTOExtended;
	@NotNull
	private DelivererDTOBasic delivererDTOBasic;
}
