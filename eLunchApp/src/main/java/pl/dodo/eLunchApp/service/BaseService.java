package pl.dodo.eLunchApp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.repository.BaseRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class BaseService {

    protected static <T,D> List<D> getAllEntites(JpaRepository<T,?> repository, Function<T,D> mapToDtoExtended){
        return repository.findAll().stream()
                .map(mapToDtoExtended)
                .toList();
    }

    protected static <T,D> Result<Void> addEntity(D entityDto, JpaRepository<T,?> repository, Function<D,T> mapToEntity){
        T entity = mapToEntity.apply(entityDto);
        repository.save(entity);
        return Result.success(null);
    }

    protected static <T> Result<Void> deleteEntity (UUID uuid, BaseRepository<T> repository){
        Optional<T> byUuid = repository.findByUuid(uuid);

        byUuid.ifPresent(repository::delete);

        return byUuid.isPresent() ? Result.success(null) :
                Result.failure(new eLunchError.ObjectNotFound(byUuid.getClass()));
    }

    protected static <T,D> Result<D> getEntityByUuid(UUID uuid, BaseRepository<T> repository, Function<T,D> mapToDtoExtended, Class<T> entity) {
        return repository.findByUuid(uuid)
                .map(mapToDtoExtended)
                .map(Result::success)
                .orElse(Result.failure(new eLunchError.ObjectNotFound(entity)));
    }

    protected static <T> Result<List<T>> validateList(List<T> listToValidate, Class<T> entity, ValidationService<T> service){
        Map<Boolean, List<Result<T>>> collect = listToValidate.stream()
                .map(service::validate)
                .collect(Collectors.partitioningBy(Result::isSuccess));

        if (!collect.get(false).isEmpty())
            return Result.failure(new eLunchError.InvalidValidation(entity));

        return Result.success(collect.get(true).stream()
                .map(Result::getData)
                .toList());
    }

    protected static <T> Result<T> validateObject(T object, ValidationService<T> service){
        Result<T> validate = service.validate(object);

        if (!validate.isSuccess())
            return Result.failure(new eLunchError.InvalidValidation(object.getClass()));

        return validate;
    }
}
