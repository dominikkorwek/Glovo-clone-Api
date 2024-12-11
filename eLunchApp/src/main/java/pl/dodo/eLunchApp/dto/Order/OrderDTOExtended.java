package pl.dodo.eLunchApp.dto.Order;

import jakarta.validation.constraints.*;
import lombok.Data;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTOExtended {

	@Nullable
	private DiscountCodeDTOBasic discountCodeDTO;

	@Nullable
	private String note;

	@NotNull
	private DeliveryAddressDTOExtended deliveryAddressDTOExtended;

	@NotNull
	@Size(min = 1)
	private List<OrderDTOExtended> orderItemDTOS;

	@NotNull
	private OrderDTOBasic orderDTOBasic;
}
