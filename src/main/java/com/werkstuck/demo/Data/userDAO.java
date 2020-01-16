package com.werkstuck.demo.Data;

import com.werkstuck.demo.Model.User;
import com.werkstuck.demo.Model.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class userDAO implements UserRepository<User> {
    private List<User> users = new ArrayList<>();

    public userDAO() {
        users.add(new User("Bent", "leicht"));
    }


    public User findByUsername(String username) {
        int index = -1;
        for(User user : users){
            if(user.getUsername().equals(username)){
                index = users.indexOf(user);
            }
        }
        return users.get(index);
    }

    @Override
    public void save(User user){
        users.add(user);
    }



    @Override
    public void delete(User user) {

    }

}


