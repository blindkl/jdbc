package pl.sda.jdbcjpa.jpa;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.sda.jdbcjpa.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter //opcjonalne - hibernate poradzi sobie bez setterów i getterów
@Setter
@ToString(exclude = "transientField")
@Entity //must have
@Table(name = "Customers") //opcjonalne -> zmiana nazwy tabeli
@NamedQueries(
        {@NamedQuery(name = "findByFirstName",
                query = "select c from Customer c where c.firstName = :fn")}
)
public class Customer extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer age; //int Integer

    private String pesel;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    private List<Order> orders = Lists.newArrayList();

    @Embedded //element zapisany w tej samej tabeli w SQLu, ale znajdujacy sie w innym obiekcie w javie
    private Address address;

    @Enumerated(EnumType.STRING)  // zapisujemy wartosc "słowną" enuma
    private CustomerStatus customerStatus;

    @ElementCollection
    private Set<String> nickname = new HashSet<String>();

    @OneToOne(mappedBy = "cust",cascade = CascadeType.ALL) // mappedBy wskazuje wlasciciela relacji (w tym przypadku druga encja) - wlasciciel przechowuje klucz obcy
    private Cart cart;

    @Transient //to nie bedzie sie zapisywac
    private String transientField;
}
//POJO plain old java object