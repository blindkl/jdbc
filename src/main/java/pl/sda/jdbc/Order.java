package pl.sda.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private BigDecimal totalCost;

    private String customerName;

    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLine;

    @ManyToOne
    private Customer customer;

}
