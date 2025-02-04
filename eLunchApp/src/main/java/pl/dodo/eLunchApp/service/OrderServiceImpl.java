package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.dto.OperationEvidence.OperationEvidenceDTOBasic;
import pl.dodo.eLunchApp.dto.OperationEvidence.OperationEvidenceDTOExtended;
import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.dto.Order.OrderDTOExtended;
import pl.dodo.eLunchApp.dto.OrderStatus.OrderStatusDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.enums.EvidenceType;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.OrderMapper;
import pl.dodo.eLunchApp.model.*;
import pl.dodo.eLunchApp.repository.OrderRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "orders")
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseService implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final DiscountCodeService discountCodeServiceImpl;
    private final OrderItemService orderItemService;
    private final DelivererService delivererServiceImpl;
    private final RestaurantService restaurantService;

    @Override
    @Cacheable("orders")
    public List<OrderDTOBasic> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    public void add(OrderDTOExtended dtoExtended) {
        addEntity(dtoExtended, orderRepository, orderMapper::mapToEntity);
    }

    @Override
    @CacheEvict(cacheNames = "orders", allEntries = true)
    public void edit(UUID uuid, OrderDTOExtended orderDTOExtended) throws eLunchError.ObjectNotFound, eLunchError.InvalidUuid {
        UUID dtoUUid = orderDTOExtended.getOrderDTOBasic().getUuid();
        if (!dtoUUid.equals(uuid))
            throw new eLunchError.InvalidUuid(dtoUUid,uuid);

        Order byUuid = orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new eLunchError.ObjectNotFound(Order.class));

        Order orderNew = orderMapper.mapToEntity(orderDTOExtended);

        DiscountCode discountCode = validateObject(orderNew.getDiscountCode(), discountCodeServiceImpl);
        List<OrderItem> orderItems = validateList(orderNew.getOrderItems(), orderItemService);
        User user = validateObject(orderNew.getUser(), userService);
        Deliverer deliverer = validateObject(orderNew.getDeliverer(), delivererServiceImpl);
        Restaurant restaurant = validateObject(orderNew.getRestaurant(), restaurantService);

        orderNew.setDiscountCode(discountCode);
        orderNew.setOrderItems(orderItems);
        orderNew.setUser(user);
        orderNew.setDeliverer(deliverer);
        orderNew.setRestaurant(restaurant);

        byUuid.edit(orderNew);
    }

    @SneakyThrows
    @Override
    @CacheEvict(cacheNames = "orders", key = "#uuid")
    public void delete(UUID uuid) {
        deleteEntity(uuid,orderRepository);
    }

    @Override
    public OrderDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid,orderRepository,orderMapper::mapToDtoExtended,Order.class);
    }

    @SneakyThrows

    @Override
    public void setIsPaid(UUID uuid) {
        Order byUuid = orderRepository.findByUuid(uuid)
                .orElseThrow( () ->new eLunchError.ObjectNotFound(Order.class));

        if (byUuid.getOrderStatus().getIsPaid())
            throw  new eLunchError.WrongOrderStatus(byUuid.getOrderStatus());

        byUuid.getOrderStatus().setIsPaid(true);
        orderRepository.save(byUuid);
    }

    @SneakyThrows
    @Override
    public void setIsDelivered(UUID uuid, OrderStatusDTOBasic basicOrderStatusDto) {
        Order byUuid = orderRepository.findByUuid(uuid)
                .orElseThrow( () ->new eLunchError.ObjectNotFound(Order.class));

        if (!byUuid.getOrderStatus().getIsPaid() || byUuid.getOrderStatus().getGiveOutTime() == null)
            throw  new eLunchError.WrongOrderStatus(byUuid.getOrderStatus());

        byUuid.getOrderStatus().setDeliveryTime(basicOrderStatusDto.getDeliveryTime());
        orderRepository.save(byUuid);
    }

    @SneakyThrows
    @Override
    public void setIsGivenOut(UUID uuid, OrderStatusDTOBasic basicOrderStatusDto) {
        Order byUuid = orderRepository.findByUuid(uuid)
                .orElseThrow( () ->new eLunchError.ObjectNotFound(Order.class));

        if (!byUuid.getOrderStatus().getIsPaid() || byUuid.getOrderStatus().getDeliveryTime() == null)
            throw new eLunchError.WrongOrderStatus(byUuid.getOrderStatus());

        byUuid.getOrderStatus().setGiveOutTime(basicOrderStatusDto.getGiveOutTime());
        orderRepository.save(byUuid);
    }

    @Override
    public UserDTOExtended newOperationForPaidOrder(OrderDTOBasic basicOrderDto) {
        UserDTOExtended byUuid = userService.getByUuid(basicOrderDto.getUser().getUuid());

        byUuid.setOperationEvidence(List.of(newEwidenceForOrderPayment(basicOrderDto)));
        return byUuid;
    }

    private OperationEvidenceDTOExtended newEwidenceForOrderPayment(OrderDTOBasic orderDTO) {
        return OperationEvidenceDTOExtended.builder()
                .operationEvidenceDTOBasic(OperationEvidenceDTOBasic.builder()
                        .date(Instant.now())
                        .type(EvidenceType.PAYMENT)
                        .user(orderDTO.getUser())
                        .build())
                .amount(orderDTO.getPrice())
                .build();
    }

    @Override
    public Order validate(Order object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(orderRepository, object.getUuid(), Order.class);
    }
}

