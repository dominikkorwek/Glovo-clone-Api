package pl.dodo.eLunchApp.dto.OrderItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;

@Data
public class OrderItemDtoExtended {
	@NotNull
	@Min(1)
	private Integer quantity;

	@NotNull
	private MenuItemDTOExtended menuItemDTO;

	@NotNull
	private OrderItemDTOBasic orderItemDTOBasic;
}
