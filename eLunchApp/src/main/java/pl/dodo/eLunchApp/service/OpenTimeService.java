package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOBasic;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OpenTimeService {
    List<OpenTimeDTOBasic> getAll();
    Result<Void> put(UUID uuid, OpenTimeDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<OpenTimeDTOExtended> getByUuid(UUID uuid);
}
