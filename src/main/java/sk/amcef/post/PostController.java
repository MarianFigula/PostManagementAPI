package sk.amcef.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mypost")
public class PostController {

    @Autowired
    private IPostService service;

    private RestTemplate restTemplate;

    private final String url = "https://jsonplaceholder.typicode.com/users/";

    @GetMapping()
    public List<PostResponse> getAllPosts(){
        return this.service.getAllPosts().stream().map(PostResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest request){
        return new PostResponse(this.service.createPost(request));
    }

    // TODO: pozriet ako to spojit a ci sa to da vlastne alebo to automaticky vie oddelit post/user id
    @GetMapping(value = "/{postId}")
    public PostResponse getById(@PathVariable("postId") Integer postId){
        return new PostResponse(this.service.getById(postId));
    }

    @GetMapping(value = "/user/{userId}")
    public PostResponse getByUserId(@PathVariable("userId") Integer userId){
        restTemplate = new RestTemplate();
        if (restTemplate.getForObject(url + userId, Object.class) != null){
            return new PostResponse(this.service.getByUserId(userId));
        }
        return null;
    }


    @PatchMapping(value = "/{postId}")
    public PostResponse updatePost(@PathVariable("postId") Integer postId, @RequestBody PostRequest request){
        return new PostResponse(this.service.updatePost(postId, request));
    }

    @DeleteMapping(value = "/{postId}")
    public void deletePost(@PathVariable("postId") Integer postId){
        this.service.deletePost(postId);
    }



}
