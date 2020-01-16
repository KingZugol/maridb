package com.werkstuck.demo.Data;

import com.werkstuck.demo.Model.Product;
import com.werkstuck.demo.Model.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class productDAO implements ProductRepository {

    private List<Product> prod=new ArrayList<>();

    public productDAO(){
        prod.add(new Product("Stickers", 1));
        prod.add(new Product("Poster", 2));
        prod.add(new Product("SnackBox", 3));
    }
    @Override
    public List<Product> getAll() {
        return this.prod;
    }


    @Override
    public Product update(Product product, int id) {
        Product prodToUp=findById(id);
        if(prodToUp.getId()==id){
            prodToUp.setName(product.getName());
        }

        return prodToUp;
    }

    @Override
    public void delete(Product product) {
        this.prod.remove(product);
    }

    @Override
    public Product findById(int id) {
        int p=10;
       for(int i=0;i<prod.size();i++){
           if (prod.get(i).getId()==id){
               p=i;
           }
       }
      return this.prod.get(p);
    }
}
