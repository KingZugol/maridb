package com.werkstuck.demo.Data;

import com.werkstuck.demo.Model.Post;
import com.werkstuck.demo.Model.PostRepository;
import com.werkstuck.demo.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class postDAO implements PostRepository {
private List <Post> posts = new ArrayList<>();

public postDAO(){

    posts.add(new Post("LOL", "Not LOL","Bent"));

}

    @Override
    public List<Post> findAll() {

    return this.posts;
    }

    @Override
    public Iterable<Post> findAllById(Iterable<Long> ids) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Post post) {
        this.posts.add(post);

    }

    @Override
    public void delete(Post post) {
this.posts.remove(post);
    }


}
