package pl.dodo.eLunchApp.dto.MenuItem;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Dish.DishDTOId;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOId;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MenuItemDTOExtended {
	@Digits(integer = 10, fraction = 2)
	@Min(0)
	@NotNull
	private BigDecimal price;

	@NotNull
	@Size(min = 1)
	private List<DishDTOId> dishDTOS;

	@NotNull
	private RestaurantDTOId restaurantDTO;
}
