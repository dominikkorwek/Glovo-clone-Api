package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.dto.Order.OrderDTOExtended;
import pl.dodo.eLunchApp.dto.OrderStatus.OrderStatusDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.exceptions.eLunchError;
import pl.dodo.eLunchApp.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService extends ValidationService<Order> {
    List<OrderDTOBasic> getAll();
    void add(OrderDTOExtended dtoExtended);
    void edit(UUID uuid, OrderDTOExtended dtoExtended) throws eLunchError.ObjectNotFound, eLunchError.InvalidUuid;
    void delete(UUID uuid);
    OrderDTOExtended getByUuid(UUID uuid) throws eLunchError.ObjectNotFound;

    void setIsPaid(UUID uuid);
    void setIsDelivered(UUID uuid, OrderStatusDTOBasic orderStatusDTOBasic);
    void setIsGaveOut(UUID uuid, OrderStatusDTOBasic orderStatusDTOBasic);

    UserDTOExtended newOperationForPaidOrder(OrderDTOBasic orderDTOBasic);

    List<OrderDTOBasic> getAllbyUser(UUID userUuid);
    List<OrderDTOBasic> getAllbyDeliverer(UUID delivererUuid);

}
