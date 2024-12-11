package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Ingredient.IngredientDTOBasic;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientService {
    List<IngredientDTOBasic> getAll();
    Result<Void> put(UUID uuid, IngredientDTOBasic dtoBasic);
    Result<Void> delete(UUID uuid);
    Result<IngredientDTOBasic> getByUuid(UUID uuid);
}
