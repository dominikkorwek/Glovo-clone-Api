package pl.dodo.eLunchApp.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;
import pl.dodo.eLunchApp.enums.Archive;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User implements Editable<User> {
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

    @Nullable
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DeliveryAddress> addresses;

    @NotNull
    @Embedded
    private LoginData loginData;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<OperationEvidence> operationEvidences;

    @ManyToMany(mappedBy = "users")
    private List<DiscountCode> discountCodes;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Archive archive;

    @Override
    public void edit(User other) {
        personalData = other.personalData;
        for (int i = 0; i < orders.size(); ++i)
            orders.get(i).edit(other.orders.get(i));
        if (addresses == null || other.addresses == null)
            addresses = other.addresses;
        else
            for (int i = 0;  i < addresses.size(); ++i)
                addresses.get(i).edit(other.addresses.get(i));
        loginData = other.loginData;
        for (int i = 0; i < discountCodes.size(); ++i)
            discountCodes.get(i).edit(other.discountCodes.get(i));
        archive = other.archive;
    }
}
