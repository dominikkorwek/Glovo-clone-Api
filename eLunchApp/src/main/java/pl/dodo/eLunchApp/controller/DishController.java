package pl.dodo.eLunchApp.controller;

import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dodo.eLunchApp.validator.GroupsValidator;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    interface DishDataUpdateValidation extends Default, GroupsValidator.DishDataUpdateValidation {}

}
