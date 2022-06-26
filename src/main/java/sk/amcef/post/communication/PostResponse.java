package sk.amcef.post.communication;

import lombok.Data;
import lombok.Getter;
import sk.amcef.post.data.Post;

@Getter
@Data
public class PostResponse {
    private final Integer id;
    private final Integer userId;
    private final String title;
    private final String body;

    public PostResponse(Post post){
        this.id = post.getId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.body = post.getBody();
    }
}
