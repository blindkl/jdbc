package pl.sda.jdbcjpa.jpa;


import lombok.Getter;
import lombok.Setter;
import pl.sda.jdbcjpa.BaseEntity;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class Cart extends BaseEntity {

    @OneToOne
    private Customer cust; //uzyte w mmappedBy w Customerze
}
