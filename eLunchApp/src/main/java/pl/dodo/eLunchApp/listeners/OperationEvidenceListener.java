package pl.dodo.eLunchApp.listeners;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.events.OperationEvidenceCreator;
import pl.dodo.eLunchApp.mapper.OperationEvidenceMapper;
import pl.dodo.eLunchApp.model.OperationEvidence;
import pl.dodo.eLunchApp.model.User;
import pl.dodo.eLunchApp.repository.UserRepository;
import pl.dodo.eLunchApp.service.OperationEvidenceService;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OperationEvidenceListener {

    private final OperationEvidenceService operationEvidenceService;
    private final UserRepository userRepository;
    private final OperationEvidenceMapper mapper;

    @EventListener
    public void onAddOperation(OperationEvidenceCreator operationEvidenceCreator){
        UserDTOExtended userDTO = operationEvidenceCreator.getUserDTO();
        OperationEvidence operationEvidence = userDTO.getOperationEvidence().stream()
                .findFirst()
                .map(mapper::mapToEntity)
                .orElseThrow();

        User user = userRepository.findByUuid(userDTO.getUserDTOBasic().getUserDTOId().getUuid()).orElseThrow();
        operationEvidence.setUser(user);

        validateAccountBalanceAfterOperation(operationEvidence);
        operationEvidenceService.put(operationEvidence);
    }

    @SneakyThrows
    public void validateAccountBalanceAfterOperation(OperationEvidence operationEvidence){
        BigDecimal accountBalaceAfterOperation = operationEvidenceService.getAccountBalaceAfterOperation(operationEvidence);

        if (accountBalaceAfterOperation.compareTo(BigDecimal.ZERO) <= 0)
            throw new Exception();
        //todo
    }
}
