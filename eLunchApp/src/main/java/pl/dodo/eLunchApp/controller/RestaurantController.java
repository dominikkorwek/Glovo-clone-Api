package pl.dodo.eLunchApp.controller;

import jakarta.validation.groups.Default;
import pl.dodo.eLunchApp.validator.GroupsValidator;

public class RestaurantController {

    interface DataUpdateValidation extends Default, GroupsValidator.RestaurantDataUpdateValidation {}
}
