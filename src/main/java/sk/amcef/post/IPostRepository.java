package sk.amcef.post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends CrudRepository<Post, Integer> {

    List<Post> findAll();

    Post findPostById(Integer id);

    Post findPostByUserId(Integer userId);

}
