package pl.dodo.eLunchApp.controller;

import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping(value = "/deliverers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DelivererController {

    interface NewDelivererValidation extends Default, GroupsValidator.DelivererNewDelivererValidation {}

    private final DelivererService delivererService;

    @GetMapping
    public ResponseEntity<List<DelivererDTOBasic>> getAll(){
        return new ResponseEntity<>(delivererService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DelivererDTOExtended> get(@PathVariable UUID uuid, HttpServletRequest request){
        return null;
    }

    @PutMapping("/{uuid}")
    @Transactional
    @Validated(NewDelivererValidation.class)
    public ResponseEntity<Void> put(@PathVariable UUID uuid, @RequestBody @Valid DelivererDTOExtended basicDelivererDto){
        return null;
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        return null;
    }
}
