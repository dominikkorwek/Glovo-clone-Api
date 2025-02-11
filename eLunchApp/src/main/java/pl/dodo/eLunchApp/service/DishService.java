package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Dish.DishDTOExtended;
import pl.dodo.eLunchApp.dto.Dish.DishDTOId;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.Dish;

import java.util.List;
import java.util.UUID;

public interface DishService extends ValidationService<Dish> {
    List<DishDTOId> getAll();
    void add(DishDTOExtended dtoExtendedd);
    void edit(UUID uuid, DishDTOExtended dtoExtendedd) throws eLunchError.ObjectNotFound, eLunchError.InvalidUuid;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    DishDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
