package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Dish implements Editable<Dish> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    @Convert(converter = UUIDConverter.class)
    private UUID uuid;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    @OneToOne
    private Product product;

    @ManyToMany(mappedBy = "dishes")
    private List<MenuItem> menuItems;

    @Override
    public void edit(Dish other) {
        quantity = other.quantity;
        product.edit(other.product);
        for (int i = 0;  i < menuItems.size(); ++i)
            menuItems.get(i).edit(other.menuItems.get(i));
    }
}
