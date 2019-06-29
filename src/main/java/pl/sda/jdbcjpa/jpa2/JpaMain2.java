package pl.sda.jdbcjpa.jpa2;

import com.google.common.collect.Lists;
import pl.sda.jdbcjpa.JpaDao;

import javax.persistence.EntityManager;

public class JpaMain2 {
    public static void main(String[] args) {
        addPost("title1", "body1");
        addPost("title2", "body2");
        Integer post3Id = addPost("title3", "body3");
        addPost("title4", "body4");

        findPostById(post3Id);
    }

    private static Post findPostById(Integer id) {
        EntityManager em = JpaDao.getEM();
        Post post = em.find(Post.class, id);
        System.out.println(post);
        return post;
    }

    private static Integer addPost(String title, String body) {
        Comment comment1 = new Comment();
        Tag tag1 = new Tag("java");
        Tag tag2 = new Tag("jpa");
                
        Post post = Post.builder().title(title).body(body).tags(Lists.newArrayList(tag1, tag2)).build();

        post.addComment(comment1);

        System.out.println("Przed zapisem: " + post);
        JpaDao.saveEntity(post);
        System.out.println("Po zapisie: " + post);
        return post.getId();
    }
}
