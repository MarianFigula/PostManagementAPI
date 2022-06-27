package sk.amcef.post.logic;

import sk.amcef.exceptions.NotFoundException;
import sk.amcef.post.communication.PostRequest;
import sk.amcef.post.data.Post;

import java.util.List;

public interface IPostService {

    String URL = "https://jsonplaceholder.typicode.com/users/";

    List<Post> getAllPosts();

    List<Post> getAllPostsByUserId(Integer userId) throws NotFoundException;

    Post createPost(PostRequest request) throws NotFoundException;

    Post getById(Integer postId) throws NotFoundException;

    Post getByUserId(Integer userId) throws NotFoundException;

    Post updatePost(Integer postId, PostRequest request) throws NotFoundException;

    void deletePost(Integer postId) throws NotFoundException;
}
