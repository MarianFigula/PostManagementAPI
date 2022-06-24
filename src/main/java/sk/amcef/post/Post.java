package sk.amcef.post;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // TODO: bacha toto ma asi managovat externa
    private Integer id;

    private Integer userId;
    private String title;
    private String body;
}
