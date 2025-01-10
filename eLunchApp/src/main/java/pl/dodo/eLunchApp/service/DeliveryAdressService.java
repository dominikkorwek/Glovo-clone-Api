package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.UUID;

public interface DeliveryAdressService {
    List<DeliveryAddressDTOExtended> getAll();
    Result<Void> add(DeliveryAddressDTOExtended dtoExtended);
    Result<Void> edit(UUID uuid, DeliveryAddressDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<DeliveryAddressDTOExtended> getByUuid(UUID uuid);
}
