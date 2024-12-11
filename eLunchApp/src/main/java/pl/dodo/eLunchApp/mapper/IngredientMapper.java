package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.Ingredient.IngredientDTOBasic;
import pl.dodo.eLunchApp.model.Ingredient;

@Mapper(componentModel = "Spring")
public interface IngredientMapper {

    IngredientDTOBasic mapToDtoBasic(Ingredient ingredient);

    @InheritInverseConfiguration
    Ingredient mapToEntity(IngredientDTOBasic dtoBasic);
}