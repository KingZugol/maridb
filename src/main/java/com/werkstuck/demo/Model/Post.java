package com.werkstuck.demo.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import javax.persistence.*;

@Entity
@JsonIgnoreProperties
public class Post {
    private Long id;
    private String title;
    private String body;
    private User author;
    private int weedId;

    public Post(){}
    public Post(String title, String body, User author, int weedId) {
        this.weedId=weedId;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public int getWeedId() {
        return weedId;
    }

    public void setWeedId(int weedId) {
        this.weedId = weedId;
    }

    private Date date = new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @OneToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", weedId=" + weedId +
                ", date=" + date +
                '}';
    }
}

