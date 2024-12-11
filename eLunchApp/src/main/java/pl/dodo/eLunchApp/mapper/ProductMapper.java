package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Product.ProductDTOBasic;
import pl.dodo.eLunchApp.dto.Product.ProductDTOExtended;
import pl.dodo.eLunchApp.model.Product;

@Mapper(componentModel = "Spring", uses = {IngredientMapper.class, DishMapper.class,
        ProductMapper.class})
public interface ProductMapper {

    ProductDTOBasic mapToDtoBasic(Product product);

    ProductDTOExtended mapToDtoExtended(Product product);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    Product mapToEntity(ProductDTOExtended dtoExtended);
}
