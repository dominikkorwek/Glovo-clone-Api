package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOBasic;
import pl.dodo.eLunchApp.dto.Discount.DiscountCodeDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.DiscountCodeMapper;
import pl.dodo.eLunchApp.model.DiscountCode;
import pl.dodo.eLunchApp.model.Restaurant;
import pl.dodo.eLunchApp.model.User;
import pl.dodo.eLunchApp.repository.DiscountCodeRepository;

import java.util.List;
import java.util.Optional;
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
    @CacheEvict(cacheNames = "discountCodes", allEntries = true)
    public Result<Void> put(UUID uuid, DiscountCodeDTOExtended dtoExtended) {
        UUID dtoUuid = dtoExtended.getDiscountCodeDTOBasic().getUuid();
        if (dtoUuid.equals(uuid))
            return Result.failure(new eLunchError.InvalidUuid(dtoUuid,uuid));

        Optional<DiscountCode> byUuid = discountCodeRepository.findByUuid(uuid);
        if (byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(DiscountCode.class));

        DiscountCode discountCode = byUuid.get();

        Result<List<User>> userListResult = validateList(discountCode.getUsers(), User.class, userService);
        if (!userListResult.isSuccess())
            return Result.failure(userListResult.getError());

        Result<List<Restaurant>> restaurantListResult = validateList(discountCode.getRestaurants(), Restaurant.class, restaurantService);
        if (!restaurantListResult.isSuccess())
            return Result.failure(restaurantListResult.getError());

        discountCode.setUsers(userListResult.getData());
        discountCode.setRestaurants(restaurantListResult.getData());
        return Result.success(null);
    }

    @Override
    @CacheEvict(cacheNames = "discountCodes", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,discountCodeRepository);
    }

    @Override
    public Result<DiscountCodeDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,discountCodeRepository,discountCodeMapper::mapToDtoExtended, DiscountCode.class);
    }
}
