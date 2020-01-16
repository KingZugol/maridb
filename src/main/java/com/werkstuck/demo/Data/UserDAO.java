package com.werkstuck.demo.Data;

import com.werkstuck.demo.Model.User;
import com.werkstuck.demo.Model.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class userDAO implements UserRepository {
    private List<User> users = new ArrayList<>();

    public userDAO() {
        users.add(new User("Bent", "leicht"));
    }

    @Override
    public User findByUsername(String username) {
       int usr=0;
        for (int i = 0; i < users.size(); i++) {
            if (this.users.get(i).equals(username)) {
               usr=i;
            }
        } return this.users.get(usr);
    }
    @Override
    public void save(User user){
        users.add(user);
    }
}


