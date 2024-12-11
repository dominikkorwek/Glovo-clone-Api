package pl.dodo.eLunchApp.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import pl.dodo.eLunchApp.dto.User.UserDTOExtended;

@Getter
public class OperationEvidenceCreator extends ApplicationEvent {

    private final UserDTOExtended userDTO;

    public OperationEvidenceCreator(Object source, UserDTOExtended userDTO) {
        super(source);
        this.userDTO = userDTO;
    }
}
