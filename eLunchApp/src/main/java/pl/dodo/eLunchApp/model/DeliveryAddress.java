package pl.dodo.eLunchApp.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.dodo.eLunchApp.converter.UUIDConverter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class DeliveryAddress extends Address<DeliveryAddress> {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    @Convert(converter = UUIDConverter.class)
    private UUID uuid;

    @Nullable
    private String description;

    @NotNull
    @ManyToOne
    private User user;

    @Override
    public void edit(DeliveryAddress other) {
        super.edit(other);
        uuid = other.uuid;
        description = other.description;
        user.edit(other.user);
    }
}
