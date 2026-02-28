package com.example.productApp.Repository;

import com.example.productApp.Model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();
    private Long idCounter = 1L;

    public List<Product> findAll(){
        return productList;
    }
    public void save(Product product){
        if(product.getId() == null){
            product.setId(idCounter++);
            productList.add(product);
        }else{
            update(product);
        }
    }
   public Product returnSavedProduct(Product product){
       if(product.getId() == null){
           product.setId(idCounter++);
           productList.add(product);
       }else{
           update(product);
       }
       return product;
   }

    public Optional<Product> findById(Long id){
        return productList.stream().filter(p -> p.getId().equals(id))
                .findFirst();
    }
    public void deleteById(Long id){
        productList.removeIf(p -> p.getId().equals(id));
    }
    private  void update(Product updateProdudct){
        for(int i=0;i<productList.size();i++){
            if(productList.get(i).getId().equals(updateProdudct.getId())){
                productList.set(i,updateProdudct);
                break;
            }
        }
    }

}
