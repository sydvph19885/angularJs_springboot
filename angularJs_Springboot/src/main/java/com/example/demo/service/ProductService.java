package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    IProductRepository repository;

    public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repository.findAll(pageable));
    }

    public ResponseEntity<List<Product>> delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok(repository.findAll());
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    public ResponseEntity<List<Product>> add(Product product) {
        repository.save(product);
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Product> getOne(Integer id) {
        if (repository.existsById(id)) {
            return ResponseEntity.ok(repository.findById(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<Product> findByProductNameContains(String name) {
        return repository.findByProductNameContains(name);
    }
}
