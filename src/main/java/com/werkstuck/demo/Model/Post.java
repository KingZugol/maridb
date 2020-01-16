package com.werkstuck.demo.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;



@JsonIgnoreProperties
public class Post {

    private static int ID_COUNTER = 0;
    private int postId;
    private int userId;
    private String title;
    private String body;
    private String author;
    private int weedId;

    public Post(){
        this.postId = ID_COUNTER++;
    }
    public Post(String title, String body){
        this.title = title;
        this.body = body;
    }
    public Post(String title, String body, String author, int weedId, int userId) {
        this.postId = ID_COUNTER++;
        this.weedId = weedId;
        this.title = title;
        this.body = body;
        this.author = author;
        this.userId  = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getWeedId() {
        return weedId;
    }

    public void setWeedId(int weedId) {
        this.weedId = weedId;
    }

    private Date date = new Date();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author='" + author + '\'' +
                ", weedId=" + weedId +
                ", date=" + date +
                '}';
    }
}
