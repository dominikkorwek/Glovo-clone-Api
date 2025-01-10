package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOBasic;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.mapper.OpenTimeMapper;
import pl.dodo.eLunchApp.model.OpenTime;
import pl.dodo.eLunchApp.repository.OpenTimeRepository;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "openTimes")
@RequiredArgsConstructor
public class OpenTimeServiceImpl extends BaseService implements OpenTimeService {
    private final OpenTimeRepository openTimeRepository;
    private final OpenTimeMapper openTimeMapper;

    @Override
    public List<OpenTimeDTOBasic> getAll() {
        return openTimeRepository.findAll().stream()
                .map(openTimeMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "openTimes", allEntries = true)
    public Result<Void> put(UUID uuid, OpenTimeDTOExtended openTimeDTOExtended) {
        //todo
    }

    @Override
    @CacheEvict(cacheNames = "openTimes", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,openTimeRepository);
    }

    @Override
    public Result<OpenTimeDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,openTimeRepository,openTimeMapper::mapToDtoExtended, OpenTime.class);
    }
}