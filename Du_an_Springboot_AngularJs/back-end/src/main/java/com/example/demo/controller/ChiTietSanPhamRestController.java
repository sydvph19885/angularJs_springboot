package com.example.demo.controller;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.model.PhanTrang;
import com.example.demo.service.IChiTietSanPhamService;
import com.example.demo.service.IMauSacService;
import com.example.demo.service.INhaSanXuatService;
import com.example.demo.utitil.UploadImg;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/san-pham")
@MultipartConfig
public class ChiTietSanPhamRestController {

    @Autowired
    IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    IMauSacService mauSacService;

    @Autowired
    INhaSanXuatService nhaSanXuatService;

    @Autowired
    UploadImg uploadImg;




    @GetMapping("/hien-thi")
    public ResponseEntity<PhanTrang> getAll(@RequestParam(name = "page", required = false) Optional<Integer> trangSo, @RequestParam(name = "size", required = false) Optional<Integer> size) {
        Pageable pageable = PageRequest.of(trangSo.orElse(0), size.orElse(6));
        Page<ChiTietSP> pageDanhSach = chiTietSanPhamService.getAll(pageable);
        PhanTrang phanTrang = new PhanTrang();
        phanTrang.setTongSoTrang(pageDanhSach.getTotalPages());
        phanTrang.setTrangHienTai(pageDanhSach.getNumber());
        phanTrang.setChiTietSPList(pageDanhSach.getContent());
        phanTrang.setMauSacList(mauSacService.getAll());
        phanTrang.setNhaSanXuatList(nhaSanXuatService.getAll());
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
    public ResponseEntity<ChiTietSP> getOne(@PathVariable(name = "id", required = false) String id) {
        ChiTietSP chiTietSP = chiTietSanPhamService.getOne(id);
        if (chiTietSP == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(chiTietSP);
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<PhanTrang> saveOrUpdate(@RequestBody ChiTietSP chiTietSP){
        chiTietSanPhamService.saveOrUpdate(chiTietSP);
        return ResponseEntity.ok(getAll(Optional.of(0),Optional.of(5))).getBody();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PhanTrang> delete(@PathVariable(name = "id")String id){
            chiTietSanPhamService.delete(id);
        return ResponseEntity.ok(getAll(Optional.of(0),Optional.of(5))).getBody();
    }

    @PostMapping("/upload-img/{folder}")
    public JsonNode upload(@PathParam("file") MultipartFile file,@PathVariable("folder") String folder){
        File filSave = uploadImg.save(file,folder);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("name",filSave.getName());
        node.put("size",filSave.length());
        return node;
    }
}
