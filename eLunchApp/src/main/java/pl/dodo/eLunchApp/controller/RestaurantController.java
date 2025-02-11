package pl.dodo.eLunchApp.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dodo.eLunchApp.dto.DeliveryAddress.DeliveryAddressDTOExtended;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOBasic;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOExtended;
import pl.dodo.eLunchApp.dto.Restaurant.RestaurantDTOId;
import pl.dodo.eLunchApp.service.RestaurantService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(name = "/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RestaurantController {
    interface RestaurantValid extends Default, GroupsValidator.RestaurantValid {}

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTOBasic>> getAll(){
        return new ResponseEntity<>(restaurantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<RestaurantDTOExtended> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(restaurantService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated({RestaurantValid.class ,GroupsValidator.NewObjectValid.class})
    public ResponseEntity<Void> add(@RequestBody @Valid RestaurantDTOExtended dtoExtended){
        restaurantService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    @Validated(RestaurantValid.class)
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid RestaurantDTOExtended dtoExtended){
        restaurantService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        restaurantService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
