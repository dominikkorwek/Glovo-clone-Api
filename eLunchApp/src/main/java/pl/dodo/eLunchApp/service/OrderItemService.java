package pl.dodo.eLunchApp.service;


import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.DiscountCode;
import pl.dodo.eLunchApp.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface OrderItemService extends ValidationService<OrderItem> {
    List<OrderItem> getAll();
    void add(OrderItem orderItem);
    void delete(OrderItem orderItem) throws eLunchError.ObjectNotFound;
    OrderItem getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;

    BigDecimal applyDiscount(DiscountCode discountCode, BigDecimal orderPrice);
}
