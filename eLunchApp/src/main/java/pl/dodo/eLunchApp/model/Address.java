package pl.dodo.eLunchApp.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;

@Embeddable
@Data
public class Address<T extends Address<T>> implements Editable<T> {

    @NotNull
    private String street;

    @NotNull
    private String streetNumber;

    @NotNull
    private String localNumber;

    @NotNull
    private String borough;

    @Nullable
    private String city;

    @Nullable
    private String country;

    @Nullable
    private String state;

    @Override
    public void edit(Address other) {
        street = other.street;
        streetNumber = other.streetNumber;
        localNumber = other.localNumber;
        borough = other.borough;
        city = other.city;
        country = other.country;
        state = other.state;
    }
}
