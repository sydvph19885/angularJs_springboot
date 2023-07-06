package com.example.demo.repository;

import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGioHangChiTietRepository extends JpaRepository<GioHangChiTiet,String> {
    List<GioHangChiTiet> findGioHangChiTietByGioHang(GioHang gioHang);
}
