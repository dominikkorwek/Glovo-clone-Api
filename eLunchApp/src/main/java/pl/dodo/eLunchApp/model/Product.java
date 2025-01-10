package pl.dodo.eLunchApp.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Product implements Editable<Product> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    @Convert(converter = UUIDConverter.class)
    private UUID uuid;

    @NotBlank
    private String name;

    @OneToMany
    private List<Ingredient> ingredients;

    @Nullable
    @OneToOne
    private Dish dish;

    @Override
    public void edit(Product other) {
        name = other.name;
        for (int i = 0; i < ingredients.size(); ++i)
            ingredients.get(i).edit(other.ingredients.get(i));

        if (dish == null || other.dish == null)
            dish = other.dish;
        else
            dish.edit(other.dish);
    }
}
