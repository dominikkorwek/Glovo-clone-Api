package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantService {
    List<RestaurantDTOBasic> getAll();
    Result<Void> put(UUID uuid, RestaurantDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<RestaurantDTOExtended> getByUuid(UUID uuid);
}
