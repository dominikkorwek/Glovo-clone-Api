package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.DiscountCodeMapper;
import pl.dodo.eLunchApp.model.DiscountCode;
import pl.dodo.eLunchApp.model.Restaurant;
import pl.dodo.eLunchApp.model.User;
import pl.dodo.eLunchApp.repository.DiscountCodeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "discountCodes")
public class DiscountCodeServiceImpl extends BaseService implements DiscountCodeService{
    private final DiscountCodeRepository discountCodeRepository;
    private final DiscountCodeMapper discountCodeMapper;
    private final UserService userService;
    private final RestaurantService restaurantService;

    @Override
    @Cacheable(cacheNames = "discountCodes")
    public List<DiscountCodeDTOBasic> getAll() {
        return getAllEntites(discountCodeRepository,discountCodeMapper::mapToDtoBasic);
    }

    @Override
    public void add(DiscountCodeDTOExtended dtoExtended) {
        addEntity(dtoExtended, discountCodeRepository, discountCodeMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "discountCodes", allEntries = true)
    public void edit(UUID uuid, DiscountCodeDTOExtended dtoExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        UUID dtoUuid = dtoExtended.getDiscountCodeDTOBasic().getUuid();
        if (dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid,uuid);

        DiscountCode byUuid = discountCodeRepository.findByUuid(uuid)
        .orElseThrow(() -> new eLunchError.ObjectNotFound(DiscountCode.class));

        DiscountCode discountCode = discountCodeMapper.mapToEntity(dtoExtended);

        List<Restaurant> restaurantListResult = validateList(discountCode.getRestaurants(), restaurantService);
        List<User> userListResult = validateList(discountCode.getUsers(), userService);

        discountCode.setUsers(userListResult);
        discountCode.setRestaurants(restaurantListResult);
        byUuid.edit(discountCode);
    }

    @Override
    @CacheEvict(cacheNames = "discountCodes", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,discountCodeRepository);
    }

    @Override
    public DiscountCodeDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,discountCodeRepository,discountCodeMapper::mapToDtoExtended, DiscountCode.class);
    }

    @Override
    public DiscountCode validate(DiscountCode object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(discountCodeRepository, object.getUuid(), DiscountCode.class);
    }
}
