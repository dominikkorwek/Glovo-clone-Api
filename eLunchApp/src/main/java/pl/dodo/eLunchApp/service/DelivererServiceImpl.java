package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOBasic;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.DelivererMapper;
import pl.dodo.eLunchApp.model.Deliverer;
import pl.dodo.eLunchApp.repository.DelivererRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "deliveres")
public class DelivererServiceImpl extends BaseService implements DelivererService{
    private final DelivererRepository delivererRepository;
    private final DelivererMapper delivererMapper;

    @Override
    @Cacheable(cacheNames = "deliveres")
    public List<DelivererDTOBasic> getAll() {
        return delivererRepository.findAll().stream()
                .map(delivererMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "deliveres", allEntries = true)
    public Result<Void> put(UUID uuid, DelivererDTOExtended delivererDto) {
        //todo
    }


    @Override
    @CacheEvict(cacheNames = "deliveres", allEntries = true)
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,delivererRepository);
    }

    @Override
    public Result<DelivererDTOExtended> getByUuid(UUID uuid) {
        return getByUuid(uuid,delivererRepository,delivererMapper::mapToDtoExtended,Deliverer.class);
    }
}
