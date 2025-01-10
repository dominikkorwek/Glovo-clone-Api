package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.model.Restaurant;

import java.util.List;
import java.util.UUID;

public interface RestaurantService extends ValidationService<Restaurant> {
    List<RestaurantDTOBasic> getAll();
    Result<Void> put(UUID uuid, RestaurantDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<RestaurantDTOExtended> getByUuid(UUID uuid);
}
