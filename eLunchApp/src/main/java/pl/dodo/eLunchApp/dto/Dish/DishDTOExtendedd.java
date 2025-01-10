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
public class DishDTOExtendedd {

	private DishDTOId dishDTOId;

	@NotNull
	@Min(1)
	private Integer quantity;

	@NotNull
	private ProductDTOBasic productDTO;

	@Nullable
	@Null(groups = GroupsValidator.DishDataUpdateValidation.class)
	private List<MenuItemDTOBasic> menuItemDTOS;
}
