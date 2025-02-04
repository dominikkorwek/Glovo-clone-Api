package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.enums.EvidenceType;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
public class OperationEvidence{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Instant date;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EvidenceType type;

    @Column(scale = 2,precision = 12)
    @Digits(integer = 10, fraction = 2)
    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotNull
    @ManyToOne
    private User user;
}
