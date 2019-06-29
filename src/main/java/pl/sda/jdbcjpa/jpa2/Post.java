package pl.sda.jdbcjpa.jpa2;

import lombok.*;
import pl.sda.jdbcjpa.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Builder
public class Post extends BaseEntity {

    private String title;
    private String body;

    @OneToMany
    private List<Comment> comments;

    @ManyToMany
    private List<Tag> tags;

    public void addComment (Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setPost(this);
    }
}
