package sk.amcef.post;

import java.util.List;

public interface IPostService {

    List<Post> getAllPosts();

    Post createPost(PostRequest request);

    Post getById(Integer postId);

    Post getByUserId(Integer userId);

    Post updatePost(Integer postId, PostRequest request);

    void deletePost(Integer postId);
}
