package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.Restaurant;

import java.util.List;
import java.util.UUID;

public interface RestaurantService extends ValidationService<Restaurant> {
    List<RestaurantDTOBasic> getAll();
    void add(RestaurantDTOExtended dtoExtended);
    void edit(UUID uuid, RestaurantDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    RestaurantDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
