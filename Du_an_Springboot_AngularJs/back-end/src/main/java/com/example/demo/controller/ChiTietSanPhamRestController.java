package com.example.demo.controller;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.model.PhanTrang;
import com.example.demo.service.IChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/san-pham")
public class ChiTietSanPhamRestController {

    @Autowired
    IChiTietSanPhamService chiTietSanPhamService;


    @GetMapping("/hien-thi")
    public ResponseEntity<PhanTrang> getAll(@RequestParam(name = "page", required = false) Optional<Integer> trangSo, @RequestParam(name = "size", required = false) Optional<Integer> size) {
        Pageable pageable = PageRequest.of(trangSo.orElse(0), size.orElse(6));
        Page<ChiTietSP> pageDanhSach = chiTietSanPhamService.getAll(pageable);
        PhanTrang phanTrang = new PhanTrang();
        phanTrang.setTongSoTrang(pageDanhSach.getTotalPages());
        phanTrang.setTrangHienTai(pageDanhSach.getNumber());
        phanTrang.setChiTietSPList(pageDanhSach.getContent());
        if (pageDanhSach.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(phanTrang);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<PhanTrang> search(@RequestParam(name = "name") String tenSP) {
        Pageable pageable = PageRequest.of(0, 100);
        Page<ChiTietSP> pageDanhSach = chiTietSanPhamService.findChiTietSPBySanPham_TenContains(tenSP, pageable);
        PhanTrang phanTrang = new PhanTrang();
        phanTrang.setTongSoTrang(pageDanhSach.getTotalPages());
        phanTrang.setTrangHienTai(pageDanhSach.getNumber());
        phanTrang.setChiTietSPList(pageDanhSach.getContent());
        if (pageDanhSach.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(phanTrang);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<ChiTietSP> getOne(@PathVariable(name = "id",required = false) String id) {
        ChiTietSP chiTietSP = chiTietSanPhamService.getOne(id);
        if (chiTietSP == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(chiTietSP);
        }
    }
}
