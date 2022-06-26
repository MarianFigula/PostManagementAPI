package sk.amcef.post.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IPostRepository extends CrudRepository<Post, Integer> {

    List<Post> findAll();

    List<Post> findAllByUserId(Integer userId);

    Post findPostById(Integer id);

    Post findPostByUserId(Integer userId);

}
