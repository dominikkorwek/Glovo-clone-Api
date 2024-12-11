package pl.dodo.eLunchApp.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.Data;
import pl.dodo.eLunchApp.enums.Sex;

@Embeddable
@Data
public class PersonalData {

    @Nullable
    private String name;

    @Nullable
    private String surname;

    @Nullable
    private String phone;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Nullable
    @Email
    private String email;

}
