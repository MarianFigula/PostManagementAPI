package sk.amcef.post.data;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;
    private String title;
    private String body;
}
