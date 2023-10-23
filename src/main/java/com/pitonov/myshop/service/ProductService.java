package com.pitonov.myshop.service;

import com.pitonov.myshop.entity.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    void update(long id, Product newProduct);
    void delete(long id);
    Product findById(long id);

    List<Product> findAllByOrderByIdAsc();
    long count();
}
