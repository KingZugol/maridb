package com.werkstuck.demo.Data;

import com.werkstuck.demo.Model.Post;
import com.werkstuck.demo.Model.PostRepository;
import com.werkstuck.demo.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class postDAO implements PostRepository<Post> {
private List <Post> posts = new ArrayList<>();

public postDAO(){
    posts.add(new Post("LOL", "Not LOL", null, 1, 1));
    posts.add(new Post("LOL", "Not LOL", null, 1,1));
    posts.add(new Post("LOL", "Not LOL", null ,1, 1));
    posts.add(new Post("LOL", "Not Afpak", null,2156, 1));
}

    @Override
    public List<Post> findAll() {
    return this.posts;
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
    public Post findPost(int postId){
        int index = 0;
        for(Post byId : posts){
            if(postId == byId.getPostId()){
                index = posts.indexOf(byId);
            }
        }
        return posts.get(index);
    }

    @Override
    public void save(Post post) {
        this.posts.add(post);

    }

    public void update(Post post,String[] params) {
        post.setTitle(params[0]);
        post.setBody(params[1]);
    }


    @Override
    public void delete(Post post) {
        this.posts.remove(post);
    }


}
