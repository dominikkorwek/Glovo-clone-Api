package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOBasic;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.Deliverer;

import java.util.List;
import java.util.UUID;

public interface DelivererService extends ValidationService<Deliverer> {
    List<DelivererDTOBasic> getAll();
    void add(DelivererDTOExtended dtoExtended);
    void edit(UUID uuid, DelivererDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    DelivererDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
