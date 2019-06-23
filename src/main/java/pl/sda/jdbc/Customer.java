package pl.sda.jdbc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter // opcjonalne - hibernate sobie poradzi
@Setter
@ToString
@Entity // must have
@Table(name = "Customers") // opcjonalne

public class Customer {
    @Id // must have
    @GeneratedValue(strategy = GenerationType.AUTO) // must have
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private Integer age;
    private String pesel;


    @OneToMany

    private List<Order> orders;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // zapisujemy wartosc slowna enuma
    private CustomerStatus customerStatus;

    @ElementCollection
    private Set<String> nickname = new HashSet<String>();
}

//POJO plain old java object
