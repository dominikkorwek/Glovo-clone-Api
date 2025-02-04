package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOBasic;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.OpenTimeMapper;
import pl.dodo.eLunchApp.model.OpenTime;
import pl.dodo.eLunchApp.model.Restaurant;
import pl.dodo.eLunchApp.repository.OpenTimeRepository;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "openTimes")
@RequiredArgsConstructor
public class OpenTimeServiceImpl extends BaseService implements OpenTimeService {
    private final OpenTimeRepository openTimeRepository;
    private final OpenTimeMapper openTimeMapper;
    private final RestaurantService restaurantService;

    @Override
    public List<OpenTimeDTOBasic> getAll() {
        return openTimeRepository.findAll().stream()
                .map(openTimeMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    public void add(OpenTimeDTOExtended dtoExtended) {
        addEntity(dtoExtended, openTimeRepository, openTimeMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "openTimes", allEntries = true)
    public void edit(UUID uuid, OpenTimeDTOExtended openTimeDTOExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        UUID dtoUuid = openTimeDTOExtended.getOpenTimeDTOBasic().getUuid();
        if (!dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid, uuid);

        OpenTime byUuid = openTimeRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.InvalidUuid(uuid,dtoUuid));

        OpenTime openTimeNew = openTimeMapper.mapToEntity(openTimeDTOExtended);

        Restaurant restaurantResult = validateObject(openTimeNew.getRestaurant(), restaurantService);
        openTimeNew.setRestaurant(restaurantResult);

        byUuid.edit(openTimeNew);
    }

    @Override
    @CacheEvict(cacheNames = "openTimes", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,openTimeRepository);
    }

    @Override
    public OpenTimeDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,openTimeRepository,openTimeMapper::mapToDtoExtended, OpenTime.class);
    }

    @Override
    public OpenTime validate(OpenTime object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(openTimeRepository, object.getUuid(), OpenTime.class);
    }
}