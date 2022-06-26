package sk.amcef.post.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.amcef.exceptions.NotFoundException;
import sk.amcef.post.communication.PostRequest;
import sk.amcef.post.data.IPostRepository;
import sk.amcef.post.data.Post;

import java.util.List;
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
    public Post createPost(PostRequest request) {
        Post post = new Post();
        // TODO: ak vobec existuje taky user co ma userID
        post.setUserId(request.getUserId());
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());

        return this.postRepository.save(post);
    }

    @Override
    public Post getById(Integer postId) throws NotFoundException {
        return this.postRepository.findPostById(postId);
    }

    @Override
    public Post getByUserId(Integer userId) throws NotFoundException{
        return this.postRepository.findPostByUserId(userId);
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
