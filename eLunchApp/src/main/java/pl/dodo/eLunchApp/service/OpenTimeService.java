package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOBasic;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.OpenTime;

import java.util.List;
import java.util.UUID;

public interface OpenTimeService extends ValidationService<OpenTime> {
    List<OpenTimeDTOBasic> getAll();
    void add(OpenTimeDTOExtended dtoExtended);
    void edit(UUID uuid, OpenTimeDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    OpenTimeDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;
}
