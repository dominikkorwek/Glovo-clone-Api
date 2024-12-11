package pl.dodo.eLunchApp.dto.OperationEvidence;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class OperationEvidenceDTOExtended {
	@NotNull
	@Digits(integer = 10, fraction = 2)
	@Min(0)
	private BigDecimal amount;

	@NotNull
	private OperationEvidenceDTOBasic operationEvidenceDTOBasic;
}
