package pl.dodo.eLunchApp.service;

import pl.dodo.eLunchApp.model.OperationEvidence;
import pl.dodo.eLunchApp.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface OperationEvidenceService {
    List<OperationEvidence> getAll();
    void add(OperationEvidence operationEvidence);

    BigDecimal getUserAccountBalance(User user);
    BigDecimal getAccountBalaceAfterOperation(OperationEvidence operationEvidence);
}
