package pl.dodo.eLunchApp.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull
    private UUID uuid;

    @Column(scale = 2,precision = 12)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal price;

    @Nullable
    @ManyToOne
    private DiscountCode discountCode;

    @Column(scale = 2,precision = 12)
    @Digits(integer = 10, fraction = 2)
    @Min(0)
    @NotNull
    private BigDecimal amountToPay;

    @Nullable
    private String note;

    @NotNull
    @Embedded
    private OrderStatus orderStatus;

    @NotNull
    @Size(min = 1)
    @OneToMany
    private List<OrderItem> orderItems;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private Deliverer deliverer;

    @NotNull
    @ManyToOne
    private Restaurant restaurant;
}