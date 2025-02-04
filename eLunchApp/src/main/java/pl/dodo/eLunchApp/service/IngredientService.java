package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Ingredient.IngredientDTOBasic;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.Ingredient;

import java.util.List;
import java.util.UUID;

public interface IngredientService extends ValidationService<Ingredient> {
    List<IngredientDTOBasic> getAll();
    void add(IngredientDTOBasic dtoBasic);
    void edit(UUID uuid, IngredientDTOBasic dtoBasic) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    IngredientDTOBasic getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
