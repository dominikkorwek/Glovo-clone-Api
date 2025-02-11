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
	@Null(groups = GroupsValidator.NewObjectValid.class)
	private UUID uuid;

	@Null(groups = GroupsValidator.OrderValid.class)
	@NotNull(groups = GroupsValidator.PaidOutStatusValid.class)
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
	@Null(groups = GroupsValidator.OrderValid.class)
	private BigDecimal price;
}