package com.werkstuck.demo.Model;
import java.util.Date;
import javax.persistence.*;
import com.werkstuck.demo.Model.User;
@Entity
public class Post {



    private Long id;
    private String title;
    private String body;

    private String username;



    public Post(String title, String body,  String username) {

        this.title = title;
        this.body = body;
        this.username=username;
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
               ", username='" + username + '\'' +
                ", date=" + date +
                '}';
    }
}
