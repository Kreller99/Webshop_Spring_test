package com.example.demo.service;/*package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> readAll(){
        List<Product> products = new ArrayList<>();
        for(Product product: productRepo.readAll(products)){
            products.add(product);
        }
        return productRepo.readAll(products);
    }


    public void create(Product product) {
        productRepo.create(product);
    }
}*/
