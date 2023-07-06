package com.example.demo.repository;

import com.example.demo.entity.ChiTietSP;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChiTietSanPhamRepository extends JpaRepository<ChiTietSP,String> {

    Page<ChiTietSP> findChiTietSPByTenSanPhamContains(String tenSp, Pageable pageable);

}
