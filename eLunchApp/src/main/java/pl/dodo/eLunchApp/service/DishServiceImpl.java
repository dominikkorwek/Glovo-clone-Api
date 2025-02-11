package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Dish.DishDTOExtended;
import pl.dodo.eLunchApp.dto.Dish.DishDTOId;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.DishMapper;
import pl.dodo.eLunchApp.model.Dish;
import pl.dodo.eLunchApp.model.MenuItem;
import pl.dodo.eLunchApp.model.Product;
import pl.dodo.eLunchApp.repository.DishRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dishes")
public class DishServiceImpl extends BaseService implements DishService{

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final ProductService productService;
    private final MenuItemService menuItemService;

    @Override
    @Cacheable(cacheNames = "dishes")
    public List<DishDTOId> getAll() {
        return getAllEntites(dishRepository, dishMapper::mapToDtoId);
    }

    @Override
    public void add(DishDTOExtended dtoExtendedd) {
        addEntity(dtoExtendedd, dishRepository, dishMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "dishes", allEntries = true)
    public void edit(UUID uuid, DishDTOExtended dtoExtended) throws eLunchError.ObjectNotFound, eLunchError.InvalidUuid {
        UUID dtoUuid = dtoExtended.getDishDTOId().getUuid();
        if (!dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid,uuid);

        Dish byUuid = dishRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(Dish.class));

        Dish dishNew = dishMapper.mapToEntity(dtoExtended);

        Product productResult = validateObject(dishNew.getProduct(), productService);
        List<MenuItem> listResult = validateList(dishNew.getMenuItems(), menuItemService);

        dishNew.setProduct(productResult);
        dishNew.setMenuItems(listResult);
        byUuid.edit(dishNew);
    }

    @Override
    @CacheEvict(cacheNames = "dishes", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,dishRepository);
    }

    @Override
    public DishDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,dishRepository,dishMapper::mapToDtoExtended, Dish.class);
    }

    @Override
    public Dish validate(Dish dish) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(dishRepository, dish.getUuid(),Dish.class);
    }
}