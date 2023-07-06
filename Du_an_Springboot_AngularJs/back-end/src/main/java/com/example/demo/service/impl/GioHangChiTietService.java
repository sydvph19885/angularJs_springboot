package com.example.demo.service.impl;

import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.repository.IGioHangChiTietRepository;
import com.example.demo.service.IGioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GioHangChiTietService implements IGioHangChiTietService {

    @Autowired
    IGioHangChiTietRepository gioHangChiTietRepository;

    @Override
    public List<GioHangChiTiet> findGioHangChiTietByGioHang(GioHang gioHang) {
        return gioHangChiTietRepository.findGioHangChiTietByGioHang(gioHang);
    }
}
