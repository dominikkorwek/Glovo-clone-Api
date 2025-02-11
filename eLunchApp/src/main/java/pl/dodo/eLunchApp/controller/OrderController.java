package pl.dodo.eLunchApp.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import pl.dodo.eLunchApp.dto.Order.OrderDTOBasic;
import pl.dodo.eLunchApp.dto.Order.OrderDTOExtended;
import pl.dodo.eLunchApp.dto.OrderStatus.OrderStatusDTOBasic;
import pl.dodo.eLunchApp.events.OperationEvidenceCreator;
import pl.dodo.eLunchApp.service.OrderService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(name = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OrderController {

    interface OrderDataUpdateValid extends Default, GroupsValidator.OrderValid {}
    interface PaidOutStatusValid extends Default, GroupsValidator.PaidOutStatusValid {}
    interface GaveOutValid extends PaidOutStatusValid, GroupsValidator.GaveOutStatusValid {}
    interface DeliveryValid extends PaidOutStatusValid, GroupsValidator.DeliveryValid {}

    private final ApplicationEventPublisher applicationEventPublisher;
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTOBasic>> getAll(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping(params = {"userUuid"})
    public ResponseEntity<List<OrderDTOBasic>> getByUser(@RequestParam("userUuid") UUID userUuid){
        return new ResponseEntity<>(orderService.getAllbyUser(userUuid), HttpStatus.OK);
    }

    @GetMapping(params = {"delivererUuid"})
    public ResponseEntity<List<OrderDTOBasic>> getByDeliverer(@RequestParam("delivererUuid") UUID delivererUuid){
        return new ResponseEntity<>(orderService.getAllbyDeliverer(delivererUuid), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OrderDTOExtended> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(orderService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated({OrderDataUpdateValid.class, GroupsValidator.NewObjectValid.class})
    public ResponseEntity<Void> add(@RequestBody @Valid OrderDTOExtended dtoExtended){
        orderService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    @Validated(OrderDataUpdateValid.class)
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid OrderDTOExtended dtoExtended){
        orderService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        orderService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @Validated(PaidOutStatusValid.class)
    @PatchMapping("/{uuid}/paid")
    public ResponseEntity<Void> patchIsPaid(@PathVariable UUID uuid){
        OrderDTOBasic byUuid = orderService.getByUuid(uuid).getOrderDTOBasic();

        OperationEvidenceCreator operationEvidenceCreator = new OperationEvidenceCreator(this, orderService.newOperationForPaidOrder(byUuid));
        applicationEventPublisher.publishEvent(operationEvidenceCreator);

        return new  ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @Validated(GaveOutValid.class)
    @PatchMapping("/{uuid}/gived-out")
    public ResponseEntity<Void> patchIsGaveOut(@PathVariable UUID uuid, @RequestBody @Valid OrderStatusDTOBasic orderStatusDTOBasic) {
        orderService.setIsGaveOut(uuid, orderStatusDTOBasic);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @Validated(DeliveryValid.class)
    @PatchMapping("/{uuid}/delivered")
    public void patchIsDelivered(@PathVariable UUID uuid, @RequestBody @Valid OrderStatusDTOBasic orderStatusDTOBasic) {
        orderService.setIsDelivered(uuid, orderStatusDTOBasic);
    }
}
