package pl.dodo.eLunchApp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOBasic;
import pl.dodo.eLunchApp.dto.Employee.EmployeeDTOExtended;
import pl.dodo.eLunchApp.service.EmployeeService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTOBasic>> getAll(){
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<EmployeeDTOExtended> get(@PathVariable UUID uuid){
        return new ResponseEntity<>(employeeService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @Validated(GroupsValidator.NewObjectValid.class)
    public ResponseEntity<Void> add(@RequestBody @Valid EmployeeDTOExtended dtoExtended){
        employeeService.add(dtoExtended);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> edit(@PathVariable UUID uuid, @RequestBody @Valid EmployeeDTOExtended dtoExtended){
        employeeService.edit(uuid,dtoExtended);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        employeeService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
