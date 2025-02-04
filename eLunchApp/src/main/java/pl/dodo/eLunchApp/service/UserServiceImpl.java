package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.User.UserDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.UserMapper;
import pl.dodo.eLunchApp.model.*;
import pl.dodo.eLunchApp.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "users")
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DeliveryAdressService deliveryAdressService;
    private final OrderService orderService;
    private final DiscountCodeService discountCodeService;

    @Override
    @Cacheable("users")
    public List<UserDTOBasic> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    public void add(UserDTOExtended dtoExtended) {
        addEntity(dtoExtended, userRepository, userMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "users", allEntries = true)
    public void edit(UUID uuid, UserDTOExtended userDTOExtended) throws eLunchError.InvalidUuid, eLunchError.ObjectNotFound {
        UUID dtoUuid = userDTOExtended.getUserDTOBasic().getUserDTOId().getUuid();
        if (!dtoUuid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUuid, uuid);

        User byUuid = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(User.class));

        User userNew = userMapper.mapToEntity(userDTOExtended);

        List<Order> orders = validateList(userNew.getOrders(), orderService);
        List<DiscountCode> discountCodes = validateList(userNew.getDiscountCodes(), discountCodeService);

        if (userNew.getAddresses() != null) {
            List<DeliveryAddress> deliveryAddresses = validateList(userNew.getAddresses(), deliveryAdressService);
            userNew.setAddresses(deliveryAddresses);
        }

        userNew.setOrders(orders);
        userNew.setDiscountCodes(discountCodes);
        byUuid.edit(userNew);
    }

    @Override
    @CacheEvict(cacheNames = "users", key = "#uuid")
    public void delete(UUID uuid) throws eLunchError.ObjectNotFound {
        deleteEntity(uuid,userRepository);
    }

    @Override
    public UserDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,userRepository,userMapper::mapToDtoExtended, User.class);
    }

    @SneakyThrows
    @Override
    public void validateNewOperation(UUID uuid, UserDTOId userDtoId) {
        if (!Objects.equals(uuid,userDtoId.getUuid()))
            throw new eLunchError.InvalidUuid(userDtoId.getUuid(), uuid);

        userRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(User.class));
    }

    @Override
    public User validate(User object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(userRepository, object.getUuid(), User.class);
    }
}
