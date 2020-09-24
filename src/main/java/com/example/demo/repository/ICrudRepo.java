package com.example.demo.repository;

import com.example.demo.model.Product;

import java.util.List;

public interface ICrudRepo {

    public List<Product> readAll();

    public boolean create(Product product);

    public Product read(int id);

    public boolean update(Product product);

    public boolean delete(int id);
}
