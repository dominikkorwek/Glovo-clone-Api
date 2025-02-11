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
import pl.dodo.eLunchApp.dto.Dish.DishDTOExtended;
import pl.dodo.eLunchApp.dto.Dish.DishDTOId;
import pl.dodo.eLunchApp.service.DishService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(name = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DishController {
    interface DishValid extends Default, GroupsValidator.DishValid {}
    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishDTOId>> getAll(){
        return new ResponseEntity<>(dishService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DishDTOExtended> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(dishService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated({DishValid.class, GroupsValidator.NewObjectValid.class})
    public ResponseEntity<Void> add(@RequestBody @Valid DishDTOExtended dtoExtended){
        dishService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    @Validated(DishValid.class)
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid DishDTOExtended dtoExtended){
        dishService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        dishService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
