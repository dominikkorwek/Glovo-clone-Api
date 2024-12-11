package pl.dodo.eLunchApp.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import pl.dodo.eLunchApp.dto.User.UserDTOBasic;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;
import pl.dodo.eLunchApp.dto.User.UserDTOId;
import pl.dodo.eLunchApp.model.User;

@Mapper(componentModel = "spring",
        uses = {PersonalDataMapper.class,DeliveryAddressMapper.class, LoginDataMapper.class,
                OrderMapper.class, OperationEvidenceMapper.class, DiscountCodeMapper.class})
public interface UserMapper {

    UserDTOId mapToDtoId(User user);

    UserDTOBasic mapToDtoBasic(User user);

    UserDTOExtended mapToDtoExtended(User user);

    @InheritInverseConfiguration(name = "mapToDtoExtended")
    User mapToEntity(UserDTOExtended dtoExtended);
}
