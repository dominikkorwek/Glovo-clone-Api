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
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.mapper.OrderMapper;
import pl.dodo.eLunchApp.model.Order;
import pl.dodo.eLunchApp.repository.OrderRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "orders")
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseService implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    @Override
    @Cacheable("orders")
    public List<OrderDTOBasic> getAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::mapToDtoBasic)
                .toList();
    }

    @Override
    @CacheEvict(cacheNames = "orders", allEntries = true)
    public void put(UUID uuid, OrderDTOExtended orderDTOExtended) {
        //todo
    }

    @SneakyThrows
    @Override
    @CacheEvict(cacheNames = "orders", key = "#uuid")
    public Result<Void> delete(UUID uuid) {
        return deleteEntity(uuid,orderRepository);
    }

    @Override
    public Result<OrderDTOExtended> getByUuid(UUID uuid) {
        return getEntityByUuid(uuid,orderRepository,orderMapper::mapToDtoExtended,Order.class);
    }

    @SneakyThrows
    @Override
    public Result<Void> setIsPaid(OrderDTOExtended dto) {
        Optional<Order> byUuid = orderRepository.findByUuid(dto.getOrderDTOBasic().getUuid());

        if(byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(Order.class));

        Order order = byUuid.get();
        if (order.getOrderStatus().getIsPaid())
            return Result.failure(new eLunchError.WrongOrderStatus(order.getOrderStatus()));

        order.getOrderStatus().setIsPaid(true);
        orderRepository.save(order);

        return Result.success(null);
    }

    @SneakyThrows
    @Override
    public Result<Void> setIsDelivered(UUID uuid, OrderStatusDTOBasic basicOrderStatusDto) {
        Optional<Order> byUuid = orderRepository.findByUuid(uuid);

        if(byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(Order.class));

        Order order = byUuid.get();
        if (!order.getOrderStatus().getIsPaid() || order.getOrderStatus().getGiveOutTime() == null)
            return Result.failure(new eLunchError.WrongOrderStatus(order.getOrderStatus()));

        order.getOrderStatus().setDeliveryTime(basicOrderStatusDto.getDeliveryTime());
        orderRepository.save(order);

        return Result.success(null);
    }

    @SneakyThrows
    @Override
    public Result<Void> setIsGivenOut(UUID uuid, OrderStatusDTOBasic basicOrderStatusDto) {
        Optional<Order> byUuid = orderRepository.findByUuid(uuid);

        if(byUuid.isEmpty())
            return Result.failure(new eLunchError.ObjectNotFound(Order.class));

        Order order = byUuid.get();
        if (!order.getOrderStatus().getIsPaid() || order.getOrderStatus().getDeliveryTime() == null)
            return Result.failure(new eLunchError.WrongOrderStatus(order.getOrderStatus()));

        order.getOrderStatus().setGiveOutTime(basicOrderStatusDto.getGiveOutTime());
        orderRepository.save(order);

        return Result.success(null);
    }

    @Override
    public Result<UserDTOExtended> newOperationForPaidOrder(OrderDTOBasic basicOrderDto) {
        Result<UserDTOExtended> byUuid = userService.getByUuid(basicOrderDto.getUser().getUuid());

        if (byUuid.isSuccess())
            byUuid.getData().setOperationEvidence(List.of(newEwidenceForOrderPayment(basicOrderDto)));
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
}

