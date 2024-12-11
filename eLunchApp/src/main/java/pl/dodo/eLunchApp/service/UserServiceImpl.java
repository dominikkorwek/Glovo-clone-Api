package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.User.UserDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.UserMapper;
import pl.dodo.eLunchApp.model.User;
import pl.dodo.eLunchApp.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "users")
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Cacheable("users")
    public List<UserDTOBasic> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void put(UUID uuid, UserDTOExtended delivererDto) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "users", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,userRepository);
    }

    @Override
    public Result<UserDTOExtended> getByUuid(UUID uuid) {
        return getByUuid(uuid,userRepository,userMapper::mapToDtoExtended, User.class);
    }

    @SneakyThrows
    @Override
    public Result<Void> validateNewOperation(UUID uuid, UserDTOId userDtoId) {
        if (!Objects.equals(uuid,userDtoId.getUuid()))
            return Result.failure(new eLunchError.InvalidUuid(userDtoId.getUuid(), uuid));

        return userRepository.findByUuid(uuid)
                .map(e -> Result.<Void>success(null))
                .orElse(Result.failure(new eLunchError.ObjectNotFound(User.class)));
    }
}
