package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Dish.DishDTOExtendedd;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.Dish;

import java.util.List;
import java.util.UUID;

public interface DishService extends ValidationService<Dish> {
    List<DishDTOExtendedd> getAll();
    void add(DishDTOExtendedd dtoExtendedd);
    void edit(UUID uuid, DishDTOExtendedd dtoExtendedd) throws eLunchError.ObjectNotFound, eLunchError.InvalidUuid;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    DishDTOExtendedd getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
