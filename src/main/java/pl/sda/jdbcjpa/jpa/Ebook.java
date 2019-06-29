package pl.sda.jdbcjpa.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue("E")

public class Ebook extends Product {

}
