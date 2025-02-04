package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.User.UserDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService extends ValidationService<User>{
    List<UserDTOBasic> getAll();
    void add(UserDTOExtended dtoExtended);
    void edit(UUID uuid, UserDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound;
    void delete(UUID uuid) throws eLunchError.ObjectNotFound;
    UserDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;

    void validateNewOperation(UUID uuid, UserDTOId userDTOId);
}
