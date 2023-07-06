package com.example.demo.service.impl;

import com.example.demo.entity.MauSac;
import com.example.demo.repository.IMauSacRepository;
import com.example.demo.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacService implements IMauSacService {

    @Autowired
    IMauSacRepository mauSacRepository;

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac findById(String id) {
        return mauSacRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(MauSac mauSac) {
        mauSacRepository.saveAndFlush(mauSac);
    }
}
