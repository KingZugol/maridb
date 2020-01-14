package com.werkstuck.demo.Data;

import com.werkstuck.demo.Model.Post;
import com.werkstuck.demo.Model.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class postDAO implements PostRepository {
private List <Post> posts = new ArrayList<>();

public postDAO(){
    posts.add(new Post("LOL", "Not LOL", null, 1));
    posts.add(new Post("LOL", "Not LOL", null, 1));
    posts.add(new Post("LOL", "Not LOL", null ,1));
    posts.add(new Post("LOL", "Not Afpak", null,2156));
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

    public List<Post> findByWeedId(int weedId){
        List<Post> temp = new ArrayList<>();
        for(Post byId : posts){
            if(weedId == byId.getWeedId()){
                temp.add(byId);
            }
        }
        return temp;
     }
    @Override
    public void save(Post post) {
        this.posts.add(post);

    }



    @Override
    public void delete(Post post) {

    }


}
