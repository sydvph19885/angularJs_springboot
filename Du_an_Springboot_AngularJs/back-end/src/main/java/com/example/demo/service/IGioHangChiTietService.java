package com.example.demo.service;

import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;

import java.util.List;

public interface IGioHangChiTietService {

    List<GioHangChiTiet> findGioHangChiTietByGioHang(GioHang gioHang);
}
