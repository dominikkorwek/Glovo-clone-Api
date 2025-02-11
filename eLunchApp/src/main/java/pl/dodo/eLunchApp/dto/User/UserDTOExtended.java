package pl.dodo.eLunchApp.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.dto.LoginData.LoginDataDTOBasic;
import pl.dodo.eLunchApp.dto.OperationEvidence.OperationEvidenceDTOExtended;
import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.enums.Archive;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import javax.annotation.Nullable;
import java.util.List;

@Data
public class UserDTOExtended {
	@NotNull
	@Embedded
	private LoginDataDTOBasic loginDataDto;

	@JsonIgnore
	@Nullable
	@Null(groups = GroupsValidator.UserValid.class)
	private List<OrderDTOBasic> ordersDtos;

	@NotNull
	@Size(max = 0, groups = GroupsValidator.UserValid.class)
	@Size(min = 1, max = 1, groups = GroupsValidator.NewOperationValid.class)
	private List<OperationEvidenceDTOExtended> operationEvidenceDto;

	@Nullable
	private List<DiscountCodeDTOBasic> discountCodeDTOS;

	@NotNull
	private Archive archive;

	@NotNull
	private UserDTOBasic userDTOBasic;

}
