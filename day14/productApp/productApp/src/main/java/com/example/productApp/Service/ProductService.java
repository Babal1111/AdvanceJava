package com.example.productApp.Service;


import com.example.productApp.Model.Product;
import com.example.productApp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository repository){
        this.productRepository = repository;
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public void saveProduct(Product product){
        productRepository.save(product);
    }
    public Product returnSavedProduct(Product product){
        return productRepository.returnSavedProduct(product);
    }

    public Optional<Product> getProductById(Long id){
        return  productRepository.findById(id);
    }


    public void deleteProuductById(Long id) {
         productRepository.deleteById(id);
    }
}
