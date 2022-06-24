package sk.amcef.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
// https://jsonplaceholder.typicode.com/
// TODO: pridat prispevok - validovat userID pomocou ext. API - ci je taky pouzivatel
// TODO: zobrazenie prispevku podla id/userID - ak sa nenajde tak treba pouzit externu API
// TODO: odstranenie prispevku
// TODO: uprava prispevku - zmena title a body

@Service
public class PostService implements IPostService{

    @Autowired
    private IPostRepository postRepository;


    @Override
    public List<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    @Override
    public Post createPost(PostRequest request) {
        Post post = new Post();
        //post.setUserId(request.getUserId);
        // TODO: ak vobec existuje taky user co ma userID
        post.setUserId(request.getUserId());
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());

        return this.postRepository.save(post);
    }

    @Override
    public Post getById(Integer postId) {
        Post post = this.postRepository.findPostById(postId);
        return post;
    }

    @Override
    public Post getByUserId(Integer userId) {
        Post post = this.postRepository.findPostByUserId(userId);
        return post;
    }

    @Override
    public Post updatePost(Integer postId, PostRequest request) {
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
    public void deletePost(Integer postId) {
        this.postRepository.delete(this.getById(postId));
    }
}
