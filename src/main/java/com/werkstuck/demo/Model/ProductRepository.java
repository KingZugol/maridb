package com.werkstuck.demo.Model;

public interface ProductRepository  {
    Iterable<Product> getAll();
    Product update(Product product, int id);
    void delete(Product product);
    Product findById(int id);
}
