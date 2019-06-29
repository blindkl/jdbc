package pl.sda.jdbcjpa.jpa2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.sda.jdbcjpa.BaseEntity;

import javax.persistence.Entity;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends BaseEntity {

    private String tagName;
}
