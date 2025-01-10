package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;
import pl.dodo.eLunchApp.enums.Archive;

import java.util.UUID;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("employee")
public class Employee<T extends Employee<T>> implements Editable<T>{

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    @Convert(converter = UUIDConverter.class)
    private UUID uuid;

    @NotNull
    @Embedded
    private PersonalData personalData;

    @NotNull
    @Embedded
    private LoginData loginData;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Archive archive;

    @Override
    public void edit(Employee other) {
        personalData = other.personalData;
        loginData = other.loginData;
        archive = other.archive;
    }

}
