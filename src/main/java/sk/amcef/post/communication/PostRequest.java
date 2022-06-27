package sk.amcef.post.communication;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostRequest {
    private Integer userId;
    private String title;
    private String body;
}
