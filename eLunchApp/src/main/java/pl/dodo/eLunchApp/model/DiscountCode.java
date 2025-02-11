package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;
import pl.dodo.eLunchApp.enums.DiscountUnit;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class DiscountCode implements Editable<DiscountCode> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    @Convert(converter = UUIDConverter.class)
    private UUID uuid;

    @NotBlank
    private String code;

    @Column(scale = 2,precision = 12)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal discount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DiscountUnit discountUnit;

    @NotNull
    @Embedded
    private Period period;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<Restaurant> restaurants;

    @Override
    public void edit(DiscountCode other) {
        code = other.code;
        discount = other.discount;
        discountUnit = other.discountUnit;
        for (int i = 0;  i < users.size(); ++i)
            users.get(i).edit(other.users.get(i));
        for (int i = 0; i < restaurants.size(); ++i)
            restaurants.get(i).edit(other.restaurants.get(i));
        period = other.period;
    }
}
