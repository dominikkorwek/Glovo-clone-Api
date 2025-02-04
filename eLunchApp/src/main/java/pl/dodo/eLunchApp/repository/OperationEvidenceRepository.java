package pl.dodo.eLunchApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.dodo.eLunchApp.model.OperationEvidence;
import pl.dodo.eLunchApp.model.User;
import java.math.BigDecimal;

@Repository
public interface OperationEvidenceRepository extends JpaRepository<OperationEvidence, Long> {

    @Query("SELECT COALESCE(SUM(" +
            "CASE " +
            "WHEN e.type = pl.dodo.eLunchApp.enums.EvidenceType.DEPOSIT THEN e.amount " +
            "WHEN e.type = pl.dodo.eLunchApp.enums.EvidenceType.WITHDRAW OR " +
            "e.type = pl.dodo.eLunchApp.enums.EvidenceType.PAYMENT THEN -e.amount " +
            "ELSE 0" +
            "END),0) from OperationEvidence e where e.user = :user")
    BigDecimal getUserAccountBalance(@Param("user") User user);
}
