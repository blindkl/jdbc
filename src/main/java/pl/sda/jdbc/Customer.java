package pl.sda.jdbc;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity // must have

public class Customer {
    @Id // must have
    @GeneratedValue(strategy = GenerationType.AUTO) // must have
    private Integer id;

    private String firstName;
    private String lastName;
    private Integer age;
    private String city;
    private String postalCode;
    @OneToMany

    private List<Order> orders;


}

//POJO plain old java object
