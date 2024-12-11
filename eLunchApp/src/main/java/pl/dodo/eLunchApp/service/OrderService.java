package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.dto.Order.OrderDTOExtended;
import pl.dodo.eLunchApp.dto.OrderStatus.OrderStatusDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.exceptions.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    List<OrderDTOBasic> getAll();
    Result<Void> put(UUID uuid, OrderDTOExtended dtoExtended);
    Result<Void> delete(UUID uuid);
    Result<OrderDTOExtended> getByUuid(UUID uuid);

    Result<Void> setIsPaid(OrderDTOExtended uuid);
    Result<Void> setIsDelivered(UUID uuid, OrderStatusDTOBasic orderStatusDTOBasic);
    Result<Void> setIsGivenOut(UUID uuid, OrderStatusDTOBasic orderStatusDTOBasic);

    Result<UserDTOExtended> newOperationForPaidOrder(OrderDTOBasic orderDTOBasic);
}
