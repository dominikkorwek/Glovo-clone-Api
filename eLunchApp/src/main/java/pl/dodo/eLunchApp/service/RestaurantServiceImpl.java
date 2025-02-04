package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.RestaurantMapper;
import pl.dodo.eLunchApp.model.*;
import pl.dodo.eLunchApp.repository.RestaurantRepository;

import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "restaurants")
@RequiredArgsConstructor
public class RestaurantServiceImpl extends BaseService implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final OpenTimeService openTimeService;
    private final MenuItemService menuItemService;
    private final OrderService orderService;
    private final DiscountCodeService discountCodeService;

    @Override
    public List<RestaurantDTOBasic> getAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurantMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    public void add(RestaurantDTOExtended dtoExtended) {
        addEntity(dtoExtended, restaurantRepository, restaurantMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "restaurants", allEntries = true)
    public void edit(UUID uuid, RestaurantDTOExtended restaurantDTOExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        UUID dtoUuid = restaurantDTOExtended.getRestaurantDTOBasic().getRestaurantDTOId().getUuid();
        if (!dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid, uuid);

        Restaurant byUuid = restaurantRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(Restaurant.class));

        Restaurant restaurantNew = restaurantMapper.mapToEntity(restaurantDTOExtended);

        List<OpenTime> openTimes = validateList(restaurantNew.getOpenTimes(), openTimeService);
        List<MenuItem> menuItems = validateList(restaurantNew.getMenuItems(), menuItemService);
        List<Order> orders = validateList(restaurantNew.getOrders(), orderService);
        List<DiscountCode> discountCodes = validateList(restaurantNew.getDiscountCodes(), discountCodeService);

        restaurantNew.setOpenTimes(openTimes);
        restaurantNew.setMenuItems(menuItems);
        restaurantNew.setOrders(orders);
        restaurantNew.setDiscountCodes(discountCodes);
        byUuid.edit(restaurantNew);
    }

    @Override
    @CacheEvict(cacheNames = "restaurants", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,restaurantRepository);
    }

    @Override
    public RestaurantDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,restaurantRepository,restaurantMapper::mapToDtoExtended, Restaurant.class);
    }

    @Override
    public Restaurant validate(Restaurant object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(restaurantRepository, object.getUuid(), Restaurant.class);
    }
}