package pl.dodo.eLunchApp.dto.OperationEvidence;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.enums.EvidenceType;

import java.time.Instant;

@Builder
@Data
public class OperationEvidenceDTOBasic {
	@NotNull
	private Instant date;

	@NotNull
	private EvidenceType type;

	@NotNull
	private UserDTOId user;


}

