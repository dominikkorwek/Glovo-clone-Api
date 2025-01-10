package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.converter.UUIDConverter;
import pl.dodo.eLunchApp.enums.DayOfWeek;

import java.util.UUID;

@Entity
@Data
public class OpenTime implements Editable<OpenTime> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    @Convert(converter = UUIDConverter.class)
    private UUID uuid;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Embedded
    private PeriodTime periodTime;

    @NotNull
    @ManyToOne
    private Restaurant restaurant;

    @Override
    public void edit(OpenTime other) {
        dayOfWeek = other.dayOfWeek;
        periodTime = other.periodTime;
        restaurant.edit(other.restaurant);
    }
}
