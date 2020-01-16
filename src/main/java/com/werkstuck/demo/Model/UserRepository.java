package com.werkstuck.demo.Model;

public interface UserRepository<T> {

   void save(T t);
   void delete(T t);
}
