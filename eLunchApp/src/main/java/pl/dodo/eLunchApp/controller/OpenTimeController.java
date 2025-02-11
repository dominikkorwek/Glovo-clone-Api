package pl.dodo.eLunchApp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOBasic;
import pl.dodo.eLunchApp.dto.OpenTime.OpenTimeDTOExtended;
import pl.dodo.eLunchApp.service.OpenTimeService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(name = "/openTime", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OpenTimeController {
    private final OpenTimeService openTimeService;

    @GetMapping
    public ResponseEntity<List<OpenTimeDTOBasic>> getAll(){
        return new ResponseEntity<>(openTimeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<OpenTimeDTOExtended> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(openTimeService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated( GroupsValidator.NewObjectValid.class)
    public ResponseEntity<Void> add(@RequestBody @Valid OpenTimeDTOExtended dtoExtended){
        openTimeService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/batch")
    public void add(@RequestBody List<@Valid OpenTimeDTOExtended> opentimeDtos) {
        for (OpenTimeDTOExtended openTimeDTO : opentimeDtos)
            add(openTimeDTO);
    }

    @PutMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid OpenTimeDTOExtended dtoExtended){
        openTimeService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        openTimeService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
