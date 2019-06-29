package pl.sda.jdbcjpa.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.jdbcjpa.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order extends BaseEntity {

    private BigDecimal totalCost;

    private String customerName;

    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLine;

    @ManyToOne
    private Customer customer;

}
