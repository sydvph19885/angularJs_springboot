package com.example.demo.controller;

import com.example.demo.entity.MauSac;
import com.example.demo.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/mau-sac")
public class MauSacRestController {

    @Autowired
    IMauSacService mauSacService;

    @GetMapping("/hien-thi")
    public ResponseEntity<List<MauSac>> getAll(){
        if(mauSacService.getAll().isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(mauSacService.getAll());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<List<MauSac>> saveOrUpdate(@RequestBody MauSac mauSac){
        mauSacService.saveOrUpdate(mauSac);
        return ResponseEntity.ok(getAll()).getBody();
    }
}
