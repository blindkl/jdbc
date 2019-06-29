package pl.sda.jdbcjpa;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass // sugestia dla jpa zeby czytal anotacje klasy zwiazane z jpa
@Getter
@ToString
public abstract class BaseEntity {
    @Id // must have
    @GeneratedValue(strategy = GenerationType.AUTO) // must have
    private Integer id;
}
