package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.User.UserDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserDTOBasic> getAll();
    Result<Void> put(UUID uuid, UserDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<UserDTOExtended> getByUuid(UUID uuid);

    Result<Void> validateNewOperation(UUID uuid, UserDTOId userDTOId);
}
