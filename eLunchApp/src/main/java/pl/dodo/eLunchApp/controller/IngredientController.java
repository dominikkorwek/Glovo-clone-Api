package pl.dodo.eLunchApp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dodo.eLunchApp.dto.Ingredient.IngredientDTOBasic;
import pl.dodo.eLunchApp.service.IngredientService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(name = "/ingredient", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDTOBasic>> getAll(){
        return new ResponseEntity<>(ingredientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<IngredientDTOBasic> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(ingredientService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated( GroupsValidator.NewObjectValid.class)
    public ResponseEntity<Void> add(@RequestBody @Valid IngredientDTOBasic dtoExtended){
        ingredientService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid IngredientDTOBasic dtoExtended){
        ingredientService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        ingredientService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
