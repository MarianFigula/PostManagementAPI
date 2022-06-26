package sk.amcef.post.communication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sk.amcef.exceptions.NotFoundException;
import sk.amcef.post.logic.IPostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mypost")
public class PostController {

    @Autowired
    private IPostService service;

    @GetMapping()
    public List<PostResponse> getAllPosts(){
        return this.service.getAllPosts().stream().map(PostResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@RequestBody PostRequest request){
        return new PostResponse(this.service.createPost(request));
    }

    @GetMapping(value = "/{postId}")
    public PostResponse getById(@PathVariable("postId") Integer postId) throws NotFoundException {
        return new PostResponse(this.service.getById(postId));
    }

    // TODO: treba osetrit s internetom !!!
    @GetMapping(value = "/user/{userId}")
    public List<PostResponse> getByUserId(@PathVariable("userId") Integer userId) throws NotFoundException{
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/users/";
        if (restTemplate.getForObject(url + userId, Object.class) != null){
            return this.service.getAllPostsByUserId(userId).stream().map(PostResponse::new).collect(Collectors.toList());
        }
        return null;
    }


    @PatchMapping(value = "/{postId}")
    public PostResponse updatePost(@PathVariable("postId") Integer postId, @RequestBody PostRequest request) throws NotFoundException{
        return new PostResponse(this.service.updatePost(postId, request));
    }

    @DeleteMapping(value = "/{postId}")
    public void deletePost(@PathVariable("postId") Integer postId) throws NotFoundException{
        this.service.deletePost(postId);
    }



}
