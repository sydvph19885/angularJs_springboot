package com.example.demo.service;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.model.PhanTrang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IChiTietSanPhamService {
    Page<ChiTietSP> getAll(Pageable pageable);

    Page<ChiTietSP> findChiTietSPBySanPham_TenContains(String tenSp, Pageable pageable);

    ChiTietSP getOne(String id);
}
