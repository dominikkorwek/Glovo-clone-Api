package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class MenuItem implements Editable<MenuItem> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    @Convert(converter = UUIDConverter.class)
    private UUID uuid;

    @NotBlank
    private String name;

    @Column(scale = 2,precision = 12)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal price;

    @Size(min = 1)
    @ManyToMany
    private List<Dish> dishes;

    @ManyToOne
    private Restaurant restaurant;

    @Override
    public void edit(MenuItem other) {
        name = other.name;
        price = other.price;
        restaurant.edit(other.restaurant);
        for (int i = 0; i < dishes.size(); ++i)
            dishes.get(i).edit(other.dishes.get(i));
    }
}
