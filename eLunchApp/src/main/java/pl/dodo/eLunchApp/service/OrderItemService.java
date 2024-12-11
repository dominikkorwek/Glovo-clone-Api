package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.model.DiscountCode;
import pl.dodo.eLunchApp.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemService {
    List<OrderItem> getAll();
    Result<Void> add(OrderItem orderItem);
    Result<Void> delete(OrderItem orderItem);
    Result<OrderItem> getByUuid(UUID uuid);

    BigDecimal applyDiscount(DiscountCode discountCode, BigDecimal orderPrice);
}
