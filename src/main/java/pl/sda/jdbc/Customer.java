package pl.sda.jdbc;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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


}

//POJO plain old java object
