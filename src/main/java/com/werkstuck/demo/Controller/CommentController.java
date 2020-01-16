package com.werkstuck.demo.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.werkstuck.demo.Data.postDAO;
import com.werkstuck.demo.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    @Autowired
    public postDAO prep;

    // ObjectMapper übertragt das JSON im RequestBody in Klasse Post, wird in die PostDAO gespeichert.
    @PostMapping("/posts/{name}")
    public String postComment(Model model, @RequestBody String payload, @PathVariable("name") String name) throws JsonProcessingException {
        Post newPost = new ObjectMapper().readValue(payload, Post.class);
        prep.save(newPost);
        model.addAttribute("post", newPost);
        return "redirect:/name/" + name;
    }
    // Das Post Objekt mit der PostId gleich der im payload übertragene postId wird in der prep Liste gefunden und gelöscht
    @DeleteMapping("/posts/{name}")
    public String deleteComment(Model model, @RequestBody String payload){
        Post deletePost= prep.findPost(Integer.parseInt(payload));
        int index = deletePost.getWeedId();
        prep.delete(deletePost);
        model.addAttribute("allPost", prep.findByWeedId(index));
        return  "fragments/WeedComplete :: PostsBlock";
    }
    //Das Post Objekt mit der PostId gleich der Pfadvariablen id wird im PostDAO Liste gefunden und an das Fragment EditComment als Model gebunden.
    @GetMapping("/posts/edit/{id}")
    public String getEditComment(Model model,@PathVariable("id") int id){
        Post editPost = prep.findPost(id);
        model.addAttribute("Post", editPost);
        return "fragments/EditComment";
    }
    // Im Requestheader "postId" enthaltene Integer wird in der PostDao Liste gefunden und in der Variablen toUpdatePost gepspeichert.
    //Die im RequestBody enthaltene Strings title und Body werden an ein Objekt der Klasse Post(String title, String body) gebunden.
    //Über die DAO methode update(Post, String[]) wird der Titel und CommentBody von toUpdatePost geändert. Sollte PachMapping sein.
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
