package pl.dodo.eLunchApp.service;

import org.springframework.stereotype.Service;
import pl.dodo.eLunchApp.exceptions.Result;
import pl.dodo.eLunchApp.model.OperationEvidence;
import pl.dodo.eLunchApp.model.User;
import pl.dodo.eLunchApp.repository.OperationEvidenceRepository;

import java.math.BigDecimal;
import java.util.List;


@Service
public class OperationEvindenceServiceImpl implements OperationEvidenceService{

    private final OperationEvidenceRepository operationEvidenceRepository;

    public OperationEvindenceServiceImpl(OperationEvidenceRepository operationEvidenceRepository) {
        this.operationEvidenceRepository = operationEvidenceRepository;
    }

    @Override
    public List<OperationEvidence> getAll() {
        return operationEvidenceRepository.findAll();
    }

    @Override
    public Result<Void> add(OperationEvidence operationEvidence) {
        operationEvidenceRepository.save(operationEvidence);
        return Result.success(null);
    }

    @Override
    public BigDecimal getUserAccountBalance(User user) {
        return operationEvidenceRepository.getUserAccountBalance(user);
    }

    @Override
    public BigDecimal getAccountBalaceAfterOperation(OperationEvidence operationEvidence) {

        BigDecimal before = getUserAccountBalance(operationEvidence.getUser());

        return switch (operationEvidence.getType()){
            case DEPOSIT -> before.add(operationEvidence.getAmount());
            case WITHDRAW, PAYMENT -> before.subtract(operationEvidence.getAmount());
        };
    }
}
