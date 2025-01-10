package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;
import pl.dodo.eLunchApp.enums.Archive;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Restaurant implements Editable<Restaurant> {
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
    @Embedded
    private LoginData loginData;

    @NotNull
    @Embedded
    private CompanyData companyData;

    @Size(max = 7)
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OpenTime> openTimes;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<MenuItem> menuItems;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @ManyToMany(mappedBy = "restaurants")
    private List<DiscountCode> discountCodes;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Archive archive;

    @Override
    public void edit(Restaurant other) {
        name = other.name;
        loginData = other.loginData;
        companyData = other.companyData;
        for (int i = 0; i < openTimes.size(); ++i)
            openTimes.get(i).edit(other.openTimes.get(i));
        for (int i = 0; i < menuItems.size(); ++i)
            menuItems.get(i).edit(other.menuItems.get(i));
        for (int i = 0; i < orders.size(); ++i)
            orders.get(i).edit(other.orders.get(i));
        for (int i = 0; i < discountCodes.size(); ++i)
            discountCodes.get(i).edit(other.discountCodes.get(i));
        archive = other.archive;
    }
}
