package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOBasic;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.UUID;

public interface DelivererService {
    List<DelivererDTOBasic> getAll();
    Result<Void> put(UUID uuid, DelivererDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<DelivererDTOExtended> getByUuid(UUID uuid);
}
