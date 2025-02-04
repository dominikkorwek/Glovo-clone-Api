package pl.dodo.eLunchApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.enums.DiscountUnit;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.DiscountCode;
import pl.dodo.eLunchApp.model.OrderItem;
import pl.dodo.eLunchApp.repository.OrderItemRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "orderItems")
@RequiredArgsConstructor
public class OrderItemServiceImpl extends BaseService implements OrderItemService{

    private final OrderItemRepository orderItemRepository;

    @Override
    @Cacheable("orderItems")
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    @Override
    @CacheEvict(cacheNames = "orderItems", allEntries = true)
    public void add(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    @CacheEvict(cacheNames = "orderItems", key = "#orderItem.getUuid()")
    public void delete(OrderItem orderItem) throws eLunchError.ObjectNotFound {
        deleteEntity(orderItem.getUuid(),orderItemRepository);
    }

    @Override
    public OrderItem getByUuid(UUID uuid) throws eLunchError.ObjectNotFound {
        return getDtoByUuid(uuid, orderItemRepository, e -> e,OrderItem.class);
    }

    @Override
    public BigDecimal applyDiscount(DiscountCode discountCode, BigDecimal orderPrice) {
        if (Objects.isNull(discountCode))
            return orderPrice;

        return switch (discountCode.getDiscountUnit()){
            case DiscountUnit.PLN -> orderPrice.subtract(discountCode.getDiscount());
            case DiscountUnit.PERCENT ->
                    orderPrice.subtract(
                    orderPrice.multiply(
                            discountCode.getDiscount())
                            .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP));
        };
    }

    @Override
    public OrderItem validate(OrderItem object) throws eLunchError.ObjectNotFound {
        return getEntityByUuid(orderItemRepository, object.getUuid(), OrderItem.class);
    }
}
