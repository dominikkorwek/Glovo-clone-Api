package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOExtended;
import pl.dodo.eLunchApp.model.DiscountCode;

@Mapper(componentModel = "Spring", uses = {PeriodMapper.class, UserMapper.class, RestaurantMapper.class})
public interface DiscountCodeMapper {

    DiscountCodeDTOBasic mapToDtoBasic(DiscountCode discountCode);

    DiscountCodeDTOExtended mapToDtoExtended(DiscountCode discountCode);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    DiscountCode mapToEntity(DiscountCodeDTOExtended dtoExtended);
}
