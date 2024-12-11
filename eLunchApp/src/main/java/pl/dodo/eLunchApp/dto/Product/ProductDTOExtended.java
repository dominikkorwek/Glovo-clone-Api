package pl.dodo.eLunchApp.dto.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Dish.DishDTOExtendedd;
import pl.dodo.eLunchApp.dto.Ingredient.IngredientDTOBasic;

import javax.annotation.Nullable;
import java.util.List;

@Data
public class ProductDTOExtended {
	@NotNull
	private List<IngredientDTOBasic> ingredientDTOBasics;

	@Nullable
	private DishDTOExtendedd dishDTO;

	@NotNull
	private ProductDTOBasic productDTOBasic;
}
