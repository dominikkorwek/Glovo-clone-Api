package pl.dodo.eLunchApp.dto.Discount;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOId;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.enums.DiscountUnit;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DiscountCodeDTOExtended {

	@NotNull
	private DiscountCodeDTOBasic discountCodeDTOBasic;

	@Nullable
	private List<UserDTOId> users;

	@Nullable
	private List<RestaurantDTOId> restaurantDTOS;

	@Digits(integer = 10, fraction = 2)
	@Min(0)
	@NotNull
	private BigDecimal discount;

	@NotNull
	private DiscountUnit discountUnit;
}