package com.werkstuck.demo.Model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository  {
    Iterable<Post> findAll();

    Iterable<Post> findAllById(Iterable<Long> ids);

    Optional<Post> findById(Long id);

    void save(Post post);

    void delete(Post post);
}
