package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.model.PhanTrang;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    ProductService service;

    public Page<Product> phanTrang(Optional<Integer> trangSo) {
        Sort sort = Sort.by("productId").descending();
        Pageable pageable = PageRequest.of(trangSo.orElse(0), 10, sort);
        return service.getAll(pageable).getBody();
    }

//    @GetMapping("/home")
//    public List<Product> getAll(@RequestParam(name = "page", required = false) Optional<Integer> trangSo) {
//        return phanTrang(Optional.of(trangSo.orElse(0))).getContent();
//    }


    @GetMapping("/home")
    public PhanTrang getAll(@RequestParam(name = "page", required = false) Optional<Integer> trangSo) {
        PhanTrang phanTrang = new PhanTrang();
        phanTrang.setProducts(phanTrang(trangSo).getContent());
        phanTrang.setTrangSo(phanTrang(trangSo).getNumber());
        phanTrang.setTongTrang(phanTrang(trangSo).getTotalPages());
        return phanTrang;
    }


    @GetMapping("/search/{name}")
    public List<Product> search(@PathVariable(name = "name", required = false) String ten) {
        return service.findByProductNameContains(ten);
    }

    @DeleteMapping("/delete/{id}")
    public List<Product> delete(@PathVariable(name = "id") Integer id) {
        service.delete(id);
        return phanTrang(Optional.of(0)).getContent();
    }

    @PostMapping("/add")
    public List<Product> save(@RequestBody Product product) {
        service.add(product);
        return phanTrang(Optional.of(0)).getContent();
    }

    @PutMapping("/update")
    public List<Product> update(@RequestBody Product product) {
        service.add(product);
        return phanTrang(Optional.of(0)).getContent();
    }

    @GetMapping("/home/{id}")
    public Product getOne(@PathVariable(name = "id") Integer id) {
        return service.getOne(id).getBody();
    }
}
