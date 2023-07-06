package com.example.demo.service.impl;

import com.example.demo.entity.ChiTietSP;
import com.example.demo.model.PhanTrang;
import com.example.demo.repository.IChiTietSanPhamRepository;
import com.example.demo.service.IChiTietSanPhamService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@MultipartConfig
public class ChiTietSanPhamService implements IChiTietSanPhamService {

    @Autowired
    IChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public Page<ChiTietSP> getAll(Pageable pageable) {
        return chiTietSanPhamRepository.findAll(pageable);
    }

    @Override
    public Page<ChiTietSP> findChiTietSPBySanPham_TenContains(String tenSp, Pageable pageable) {
        return chiTietSanPhamRepository.findChiTietSPByTenSanPhamContains(tenSp, pageable);
    }

    @Override
    public ChiTietSP getOne(String id) {
        return chiTietSanPhamRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(ChiTietSP chiTietSP) {
        chiTietSanPhamRepository.saveAndFlush(chiTietSP);
    }

    @Override
    public void delete(String id) {
        chiTietSanPhamRepository.deleteById(id);
    }

    @Override
    public void uploadImage(MultipartFile file) {
        String path = "D:\\TAI_LIEU_HOC_TAP\\JAVA_6\\angularJs_Springboot_Git\\Du_an_Springboot_AngularJs\\back-end\\src\\main\\resources\\static\\image";
        File myFile = new File(path);
        if(!myFile.exists()){
            myFile.mkdirs();
        }
        File fileSave = null;
        try {
            fileSave = new File(myFile,file.getOriginalFilename());
            file.transferTo(fileSave);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
