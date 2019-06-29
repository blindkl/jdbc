package pl.sda.jdbcjpa.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.jdbcjpa.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class OrderLine extends BaseEntity {

    @ManyToOne
    private Order orderHeader; // nie używamy nazwy order, to jest słowo kluczowe sql

    private String productName;
    private BigDecimal cost;
}
