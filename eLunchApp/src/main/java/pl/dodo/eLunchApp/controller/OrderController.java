package pl.dodo.eLunchApp.controller;

import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.dto.Order.OrderDTOExtended;
import pl.dodo.eLunchApp.events.OperationEvidenceCreator;
import pl.dodo.eLunchApp.service.OrderService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
public class OrderController {

    interface OrderDataUpdateValidation extends Default, GroupsValidator.OrderValidation {}
    interface OrderStatusValidation extends Default, GroupsValidator.OrderStatusValidation {}
    interface  GiveOutValidation extends OrderStatusValidation, GroupsValidator.GiveOutStatusValidation {}
    interface  DeliveryValidation extends OrderStatusValidation, GroupsValidator.DeliveryValidation {}

    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderService orderService;

    public List<OrderDTOBasic> get() {
        return null;
    }

    public OrderDTOExtended get(@PathVariable UUID uuid){
        return null;
    }

    @Transactional
    @Validated(OrderDataUpdateValidation.class)
    public void put(@PathVariable UUID uuid, @RequestBody OrderDTOExtended dto){

    }

    //getByUser

    //getByDeliverer

    //patchIsPaid  -> zmienia zamowienie na oplacone
    @Transactional
    @Validated(OrderStatusValidation.class)
    @PatchMapping("/{userId}/paid")
    public void patchIsPaid(@PathVariable UUID userId){
        OrderDTOExtended orderDTO = orderService.getByUuid(userId)
                .orElseThrow();
        orderService.setIsPaid(orderDTO);

        OperationEvidenceCreator operationEvidenceCreator = new OperationEvidenceCreator(this, orderService.newOperationForPaidOrder(orderDTO.getOrderDTOBasic()));
        applicationEventPublisher.publishEvent(operationEvidenceCreator);
    }

    //patchIsGivedOut

    //oatchIsDelivered
}
