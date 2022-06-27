package sk.amcef.post.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sk.amcef.exceptions.NotFoundException;
import sk.amcef.post.communication.PostRequest;
import sk.amcef.post.communication.PostResponse;
import sk.amcef.post.data.IPostRepository;
import sk.amcef.post.data.Post;

import java.util.List;
import java.util.stream.Collectors;
// https://jsonplaceholder.typicode.com/
// TODO: pridat prispevok - validovat userID pomocou ext. API - ci je taky pouzivatel
// TODO: zobrazenie prispevku podla id/userID - ak sa nenajde tak treba pouzit externu API

@Service
public class PostService implements IPostService {

    @Autowired
    private IPostRepository postRepository;


    @Override
    public List<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public List<Post> getAllPostsByUserId(Integer userId){
        return this.postRepository.findAllByUserId(userId);
    }

    @Override
    public Post createPost(PostRequest request) throws NotFoundException{
        Post post = new Post();
        RestTemplate restTemplate = new RestTemplate();

        try{
            restTemplate.getForObject(this.URL + request.getUserId(), Object.class);
            post.setUserId(request.getUserId());
            post.setTitle(request.getTitle());
            post.setBody(request.getBody());
            return this.postRepository.save(post);
        }
        catch (Exception ignored){ }
        return post;
    }

    @Override
    public Post getById(Integer postId) throws NotFoundException {
        Post post = this.postRepository.findPostById(postId);
        if (post == null) {
            throw new NotFoundException();
        }
        return post;
    }

    @Override
    public Post getByUserId(Integer userId) throws NotFoundException{
        Post post = this.postRepository.findPostByUserId(userId);
        if (post == null) {
            throw new NotFoundException();
        }
        return post;
    }

    @Override
    public Post updatePost(Integer postId, PostRequest request) throws NotFoundException{
        Post post = this.getById(postId);
        if (request.getTitle() != null){
            post.setTitle(request.getTitle());
        }
        if (request.getBody() != null){
            post.setBody(request.getBody());
        }
        return this.postRepository.save(post);
    }

    @Override
    public void deletePost(Integer postId) throws NotFoundException{
        this.postRepository.delete(this.getById(postId));
    }
}
