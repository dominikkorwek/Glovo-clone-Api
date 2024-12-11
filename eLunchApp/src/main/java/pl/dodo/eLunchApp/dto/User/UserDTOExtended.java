package pl.dodo.eLunchApp.dto.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
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
	private LoginDataDTOBasic loginData;

	@JsonIgnore
	@Nullable
	@Null(groups = GroupsValidator.UserDataUpdateValidation.class)
	private List<OrderDTOBasic> orders;

	@NotNull
	@Size(max = 0, groups = GroupsValidator.UserDataUpdateValidation.class)
	@Size(min = 1, max = 1, groups = GroupsValidator.UserNewOperationValidation.class)
	private List<OperationEvidenceDTOExtended> operationEvidence;

	@Nullable
	private List<DiscountCodeDTOBasic> discountCodes;

	@NotNull
	private Archive archive;

	@NotNull
	private UserDTOBasic userDTOBasic;

}
