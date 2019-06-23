package pl.sda.jdbc;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable

public class Address {
    private String country;
    private String city;
    private String street;
    private String postalCOde;
}
