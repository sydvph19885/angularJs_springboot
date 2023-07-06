package com.example.demo.controller;

import com.example.demo.entity.NhaSanXuat;
import com.example.demo.service.INhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/nsx")
public class NhaSanXuatRestController {

    @Autowired
    INhaSanXuatService nhaSanXuatService;

    @PostMapping("/add")
    public ResponseEntity<List<NhaSanXuat>> saveOrUpdate(@RequestBody NhaSanXuat nhaSanXuat) {
        nhaSanXuatService.saveOrUpdate(nhaSanXuat);
        return ResponseEntity.ok(hienThi()).getBody();
    }

    @GetMapping("/hien-thi")
    public ResponseEntity<List<NhaSanXuat>> hienThi() {
        if (nhaSanXuatService.getAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(nhaSanXuatService.getAll());
        }
    }
}
