package com.pitonov.myshop.service;

import com.pitonov.myshop.entity.Product;
import com.pitonov.myshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(long id, Product newProduct) {
        Product found = findById(id);

        found.setName(newProduct.getName());
        found.setDescription(newProduct.getDescription());
        found.setPrice(newProduct.getPrice());
        found.setImageUrl(newProduct.getImageUrl());

        save(found);
    }

    @Override
    public void delete(long id) {
       productRepository.delete(findById(id));
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllByOrderByIdAsc() {
        return productRepository.findAllByOrderByIdAsc();
    }

    @Override
    public long count() {
        return productRepository.count();
    }
}
