package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public abstract class BaseService {

    protected static <T> Result<Void> deleteEntity (UUID uuid, BaseRepository<T> repository){
        Optional<T> byUuid = repository.findByUuid(uuid);

        byUuid.ifPresent(repository::delete);

        return byUuid.isPresent() ? Result.success(null) :
                Result.failure(new eLunchError.ObjectNotFound(byUuid.getClass()));
    }

    protected static <T,D> Result<D> getByUuid (UUID uuid, BaseRepository<T> repository, Function<T,D> mapToDtoExtended, Class<T> entity) {
        return repository.findByUuid(uuid)
                .map(mapToDtoExtended)
                .map(Result::success)
                .orElse(Result.failure(new eLunchError.ObjectNotFound(entity)));
    }
}
