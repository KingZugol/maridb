package com.werkstuck.demo.Model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository {
   User findByUsername(String username);
   void save (User user);
}
