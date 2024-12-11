package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.OperationEvidence.OperationEvidenceDTOBasic;
import pl.dodo.eLunchApp.dto.OperationEvidence.OperationEvidenceDTOExtended;
import pl.dodo.eLunchApp.model.OperationEvidence;

@Mapper(componentModel = "Spring", uses = {UserMapper.class})
public interface OperationEvidenceMapper {

    OperationEvidenceDTOBasic mapToDtoBasic(OperationEvidence operationEvidence);

    OperationEvidenceDTOExtended mapToExtended(OperationEvidence operationEvidence);

    @InheritInverseConfiguration(name = "mapToExtended")
    OperationEvidence mapToEntity(OperationEvidenceDTOExtended dtoExtended);
}
