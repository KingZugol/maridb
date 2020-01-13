package com.werkstuck.demo.Model;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Post {



    private Long id;
    private String title;
    private String body;
    private User author;


    public Post(String title, String body, User author) {

        this.title = title;
        this.body = body;
        this.author = author;
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
        return "Post ["
                + "id=" + id + ", "
                + "title=" + title + ", body=" + body + ", author=" + author + "]";
    }
}
