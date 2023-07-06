package com.example.demo.service;

import com.example.demo.entity.MauSac;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMauSacService {
    List<MauSac> getAll();

    MauSac findById(String id);

    void saveOrUpdate(MauSac mauSac);
}
