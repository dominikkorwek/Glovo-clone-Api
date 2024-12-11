package pl.dodo.eLunchApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pl.dodo.eLunchApp.enums.DayOfWeek;

import java.util.UUID;

@Entity
@Data
public class OpenTime {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
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
}
