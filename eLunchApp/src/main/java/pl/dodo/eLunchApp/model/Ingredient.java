package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;

import java.util.UUID;

@Entity
@Data
public class Ingredient implements Editable<Ingredient> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    @Convert(converter = UUIDConverter.class)
    private UUID uuid;

    @NotBlank
    private String name;

    @NotNull
    private Boolean isAllergen;

    @Override
    public void edit(Ingredient other) {
        name = other.name;
        isAllergen = other.isAllergen;
    }
}
