package pl.dodo.eLunchApp.dto.Dish;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOBasic;
import pl.dodo.eLunchApp.dto.Product.ProductDTOBasic;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import javax.annotation.Nullable;
import java.util.List;

@Data
public class DishDTOExtended {

	private DishDTOId dishDTOId;

	@NotNull
	@Min(1)
	private Integer quantity;

	@NotNull
	private ProductDTOBasic product;

	@Nullable
	@Null(groups = GroupsValidator.DishValid.class)
	private List<MenuItemDTOBasic> menuItems;
}
