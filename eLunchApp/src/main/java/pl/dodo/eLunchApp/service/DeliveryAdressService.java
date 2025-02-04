package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.DeliveryAddress;

import java.util.List;
import java.util.UUID;

public interface DeliveryAdressService extends ValidationService<DeliveryAddress> {
    List<DeliveryAddressDTOExtended> getAll();
    void add(DeliveryAddressDTOExtended dtoExtended);
    void edit(UUID uuid, DeliveryAddressDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    DeliveryAddressDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
