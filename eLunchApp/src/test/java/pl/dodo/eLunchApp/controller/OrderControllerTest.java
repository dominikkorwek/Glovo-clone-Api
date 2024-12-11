package pl.dodo.eLunchApp.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dodo.eLunchApp.dto.Order.OrderDTOExtended;
import pl.dodo.eLunchApp.service.OrderService;

import java.util.UUID;


@SpringBootTest
@RequiredArgsConstructor
class OrderControllerTest {

    private final OrderController orderController;
    private final OrderService orderService;

    private static final UUID uuid = UUID.randomUUID();

    @Test
    public void setIsPaid1(){
        //restaruacja dostawca uzytkownik kasa-dla-uzytkownika skladniki produkty dania
        orderController.patchIsPaid(uuid);

        OrderDTOExtended orderDTOExtended = orderService.getByUuid(uuid).orElseThrow();

        assert orderDTOExtended.getOrderDTOBasic().getOrderStatusDTO().getIsPaid();
    }
}