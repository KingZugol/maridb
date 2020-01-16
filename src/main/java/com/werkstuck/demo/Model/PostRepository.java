package com.werkstuck.demo.Model;


import java.util.List;

public interface PostRepository<T>  {
    List<T> findAll();
    void save(T t);
    void delete(T t);
}
