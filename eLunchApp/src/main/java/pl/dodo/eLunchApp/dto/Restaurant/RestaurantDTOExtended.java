package pl.dodo.eLunchApp.dto.Restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.dodo.eLunchApp.dto.Company.CompanyDataDTOExtended;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.dto.MenuItem.MenuItemDTOExtended;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOExtended;
import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.enums.Archive;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import javax.annotation.Nullable;
import java.util.List;

@Data
public class RestaurantDTOExtended {
	@NotNull
	@Embedded
	private CompanyDataDTOExtended companyDataDTO;

	@NotNull
	@Size(max = 7)
	private List<OpenTimeDTOExtended> openTimeDTOS;

	@Nullable
	@Null(groups = GroupsValidator.RestaurantDataUpdateValidation.class)
	private List<OrderDTOBasic> orderDTOS;

	@Nullable
	@Null(groups = GroupsValidator.RestaurantDataUpdateValidation.class)
	private List<MenuItemDTOExtended> menuItemDTOS;

	@JsonIgnore
	@NotNull
	private List<DiscountCodeDTOBasic> discountCodeDTOS;

	@NotNull
	private Archive archive;

	@NotNull
	private RestaurantDTOBasic restaurantDTOBasic;
}
