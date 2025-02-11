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
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.events.OperationEvidenceCreator;
import pl.dodo.eLunchApp.service.UserService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(name = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    interface UserValid extends Default, GroupsValidator.UserValid {}
    interface NewOperationValid extends Default, GroupsValidator.NewOperationValid {}

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public ResponseEntity<List<UserDTOBasic>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserDTOExtended> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(userService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated({UserValid.class ,GroupsValidator.NewObjectValid.class})
    public ResponseEntity<Void> add(@RequestBody @Valid UserDTOExtended dtoExtended){
        userService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    @Validated(UserValid.class)
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid UserDTOExtended dtoExtended){
        userService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        userService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @Validated(NewOperationValid.class)
    @PostMapping("/{uuid}/new-operation")
    public void postOperation(@PathVariable UUID uuid, @RequestBody @Valid UserDTOExtended dto){
        userService.validateNewOperation(uuid, dto.getUserDTOBasic().getUserDTOId());

        OperationEvidenceCreator operationEvidenceCreator = new OperationEvidenceCreator(this,dto);
        applicationEventPublisher.publishEvent(operationEvidenceCreator);
    }

    @GetMapping("/{uuid}/delivery-addresses")
    public List<DeliveryAddressDTOExtended> getUserAddresses(@PathVariable UUID uuid) {
        return userService.getAddressDTOsByUser(uuid);
    }
}