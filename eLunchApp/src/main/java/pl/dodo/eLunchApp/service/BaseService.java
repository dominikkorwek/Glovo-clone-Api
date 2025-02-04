package pl.dodo.eLunchApp.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public abstract class BaseService {

    protected static <T,D> List<D> getAllEntites(JpaRepository<T,?> repository, Function<T,D> mapToDtoExtended){
        return repository.findAll().stream()
                .map(mapToDtoExtended)
                .toList();
    }

    protected static <T,D> void addEntity(D entityDto, JpaRepository<T,?> repository, Function<D,T> mapToEntity){
        T entity = mapToEntity.apply(entityDto);
        repository.save(entity);
    }

    protected static <T> void deleteEntity (UUID uuid, BaseRepository<T> repository) throws eLunchError.ObjectNotFound {
        Optional<T> byUuid = repository.findByUuid(uuid);

        byUuid.ifPresentOrElse(repository::delete,
                () ->{throw new eLunchError.ObjectNotFound(byUuid.getClass());});
    }

    protected static <T> T getEntityByUuid(BaseRepository<T> repository, UUID uuid, Class<T> entity)
            throws eLunchError.ObjectNotFound {
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(entity));
    }

    protected static <T,D> D getDtoByUuid(UUID uuid, BaseRepository<T> repository, Function<T,D> mapToDtoExtended, Class<T> entity)
            throws eLunchError.ObjectNotFound {
        T entityByUuid = getEntityByUuid(repository, uuid, entity);
        return mapToDtoExtended.apply(entityByUuid);
    }

    protected static <T> List<T> validateList(List<T> listToValidate, ValidationService<T> service) {
        return listToValidate.stream()
            .map(service::validate)
            .toList();
    }

    protected static <T> T validateObject(T object, ValidationService<T> service) throws eLunchError.ObjectNotFound {
        return service.validate(object);
    }
}
