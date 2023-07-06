package com.example.demo.service.impl;

import com.example.demo.entity.NhaSanXuat;
import com.example.demo.repository.INhaSanXuatRepository;
import com.example.demo.service.INhaSanXuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhaSanXuatService implements INhaSanXuatService {

    @Autowired
    INhaSanXuatRepository nhaSanXuatRepository;

    @Override
    public List<NhaSanXuat> getAll() {
        return nhaSanXuatRepository.findAll();
    }

    @Override
    public NhaSanXuat getOne(String id) {
        return nhaSanXuatRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(NhaSanXuat nhaSanXuat) {
        nhaSanXuatRepository.saveAndFlush(nhaSanXuat);
    }
}
