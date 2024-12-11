package pl.dodo.eLunchApp.dto.Order;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.*;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOBasic;
import pl.dodo.eLunchApp.dto.OrderStatus.OrderStatusDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderDTOBasic {

	@NotNull
	private UUID uuid;

	@Null(groups = GroupsValidator.OrderValidation.class)
	@NotNull(groups = GroupsValidator.OrderStatusValidation.class)
	@Embedded
	private OrderStatusDTOBasic orderStatusDTO;

	@NotNull
	private UserDTOId user;

	@NotNull
	private DelivererDTOBasic delivererDTO;

	@NotNull
	private RestaurantDTOBasic restaurantDTO;

	@Digits(integer = 10, fraction = 2)
	@Min(0)
	@Null(groups = GroupsValidator.OrderValidation.class)
	private BigDecimal price;
}