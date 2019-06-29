package pl.sda.jdbcjpa.jpa2;

import lombok.Setter;
import pl.sda.jdbcjpa.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Entity
public class Comment extends BaseEntity {

    @Column(length = 20)
    private String commentBody;
    private String nickName;

    @ManyToOne
    private Post post;
}
