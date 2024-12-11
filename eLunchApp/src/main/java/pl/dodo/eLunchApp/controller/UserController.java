package pl.dodo.eLunchApp.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.events.OperationEvidenceCreator;
import pl.dodo.eLunchApp.service.UserService;
import pl.dodo.eLunchApp.validator.GroupsValidator;

import java.util.UUID;

@RequiredArgsConstructor
public class UserController {

    interface DataUpdateValidation extends Default, GroupsValidator.UserDataUpdateValidation {}
    interface NewOperationValidation extends Default, GroupsValidator.UserNewOperationValidation {}

    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    //postOperation
    @Transactional
    @Validated(NewOperationValidation.class)
    @PostMapping("/{uuid}/new-operation")
    public void postOperation(@PathVariable UUID uuid, @RequestBody @Valid UserDTOExtended dto){
        userService.validateNewOperation(uuid, dto.getUserDTOBasic().getUserDTOId());

        OperationEvidenceCreator operationEvidenceCreator = new OperationEvidenceCreator(this,dto);
        applicationEventPublisher.publishEvent(operationEvidenceCreator);
    }

    //getUserAddresses
}