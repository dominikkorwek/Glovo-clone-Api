package pl.dodo.eLunchApp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("deliverer")
@Getter
@Setter
public class Deliverer extends Employee {

    @OneToMany(mappedBy = "deliverer")
    private List<Order> orders;

}