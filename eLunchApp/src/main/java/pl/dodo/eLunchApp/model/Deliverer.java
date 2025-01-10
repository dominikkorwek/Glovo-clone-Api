package pl.dodo.eLunchApp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("deliverer")
@Getter
@Setter
public class Deliverer extends Employee<Deliverer> {

    @OneToMany(mappedBy = "deliverer")
    private List<Order> orders;

    @Override
    public void edit(Deliverer other) {
        super.edit(other);
        for(int i = 0 ; i < orders.size(); ++i)
            orders.get(i).edit(other.orders.get(i));
    }
}