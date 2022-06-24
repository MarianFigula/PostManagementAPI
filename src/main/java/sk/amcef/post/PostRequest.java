package sk.amcef.post;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequest {
    private Integer userId;
    private String title;
    private String body;
}
