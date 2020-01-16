package com.werkstuck.demo.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.werkstuck.demo.Data.postDAO;
import com.werkstuck.demo.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class CommentController {
    @Autowired
    public postDAO prep;

    @PostMapping("/posts/{name}")
    public String pPosts(Model model, @RequestBody String payload, @PathVariable("name") String name) throws JsonProcessingException {
        Post newPost = new ObjectMapper().readValue(payload, Post.class);
        prep.save(newPost);
        model.addAttribute("post", newPost);
        return "redirect:/name/" + name;
    }

    @DeleteMapping("/posts/{name}")
    public String deletePosts(Model model,@PathVariable("name") String name, @RequestBody String payload){
        Post deletePost= prep.findPost(Integer.parseInt(payload));
        int index = deletePost.getWeedId();
        prep.delete(deletePost);
        List<Post> test = prep.findByWeedId(index);
        model.addAttribute("allPost", prep.findByWeedId(index));
        return  "fragments/WeedComplete :: PostsBlock";
    }

    @GetMapping("/posts/edit/{id}")
    public String getEditComment(Model model,@PathVariable("id") int id){
        Post editPost = prep.findPost(id);
        model.addAttribute("Post", editPost);
        return "fragments/EditComment";
    }
/* PostID über Header schicken */
    @PutMapping("/posts/")
    public String putEditComment(Model model,@RequestBody String payload, @RequestHeader("postId") int postId) throws JsonProcessingException {
        Post toUpdatePost = prep.findPost(postId);
        Post editData = new ObjectMapper().readValue(payload, Post.class);
        String[] editParam =new  String[2];
        editParam[0] = editData.getTitle();
        editParam[1] = editData.getBody();
        prep.update(toUpdatePost, editParam);
        model.addAttribute("allPost", prep.findByWeedId(toUpdatePost.getWeedId()));
        return "fragments/WeedComplete :: PostsBlock";

    }
}
