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
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOBasic;
import pl.dodo.eLunchApp.dto.Deliverer.DelivererDTOExtended;
import pl.dodo.eLunchApp.service.DelivererService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(value = "/deliverer", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DelivererController {

    interface DelivererValid extends Default, GroupsValidator.DelivererValid {}

    private final DelivererService delivererService;

    @GetMapping
    public ResponseEntity<List<DelivererDTOBasic>> getAll(){
        return new ResponseEntity<>(delivererService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DelivererDTOExtended> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(delivererService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated({DelivererValid.class, GroupsValidator.NewObjectValid.class})
    public ResponseEntity<Void> add(@RequestBody @Valid DelivererDTOExtended dtoExtended){
        delivererService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    @Validated(DelivererValid.class)
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid DelivererDTOExtended dtoExtended){
        delivererService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        delivererService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
