package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Dish.DishDTOExtendedd;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.model.Dish;

import java.util.List;
import java.util.UUID;

public interface DishService extends ValidationService<Dish> {
    List<DishDTOExtendedd> getAll();
    Result<Void> put(UUID uuid, DishDTOExtendedd dtoExtendedd);
    Result<Void> delete(UUID uuid);
    Result<DishDTOExtendedd> getByUuid(UUID uuid);
}
