package pl.dodo.eLunchApp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.service.DeliveryAddressService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(value = "/deliveryAddress", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DeliveryAddressController {
    private final DeliveryAddressService deliveryAddressService;

    @GetMapping
    public ResponseEntity<List<DeliveryAddressDTOExtended>> getAll(){
        return new ResponseEntity<>(deliveryAddressService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DeliveryAddressDTOExtended> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(deliveryAddressService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated(GroupsValidator.NewObjectValid.class)
    public ResponseEntity<Void> add(@RequestBody @Valid DeliveryAddressDTOExtended dtoExtended){
        deliveryAddressService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid DeliveryAddressDTOExtended dtoExtended){
        deliveryAddressService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        deliveryAddressService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
