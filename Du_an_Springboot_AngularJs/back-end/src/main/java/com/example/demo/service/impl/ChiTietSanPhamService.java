package com.example.demo.service.impl;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.model.PhanTrang;
import com.example.demo.repository.IChiTietSanPhamRepository;
import com.example.demo.service.IChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChiTietSanPhamService implements IChiTietSanPhamService {

    @Autowired
    IChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public Page<ChiTietSP> getAll(Pageable pageable) {
        return chiTietSanPhamRepository.findAll(pageable);
    }

    @Override
    public Page<ChiTietSP> findChiTietSPBySanPham_TenContains(String tenSp, Pageable pageable) {
        return chiTietSanPhamRepository.findChiTietSPBySanPham_TenContains(tenSp, pageable);
    }

    @Override
    public ChiTietSP getOne(String id) {
        return chiTietSanPhamRepository.findById(id).get();
    }
}
