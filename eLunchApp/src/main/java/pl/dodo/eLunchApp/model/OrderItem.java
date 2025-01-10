package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;

import java.util.UUID;

@Entity
@Data
public class OrderItem implements Editable<OrderItem> {
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
    private MenuItem menuItem;

    @Override
    public void edit(OrderItem other) {
        quantity = other.quantity;
        menuItem.edit(other.menuItem);
    }
}
