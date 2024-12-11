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
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
//    @Convert(converter = UUIDConverter.class) dla jednego
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
}
