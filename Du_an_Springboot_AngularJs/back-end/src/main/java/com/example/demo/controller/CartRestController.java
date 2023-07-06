package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.ChiTietSP;
import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.service.IChiTietSanPhamService;
import com.example.demo.service.IGioHangChiTietService;
import com.example.demo.service.IGioHangService;
import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@SessionAttributes("account")
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    IGioHangService gioHangService;

    @Autowired
    IChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    HttpSession session;

    @Autowired
    IGioHangChiTietService gioHangChiTietService;

    Map<ChiTietSP, Integer> danhSachGH;

    @GetMapping("/user/hien-thi")
    public Map<ChiTietSP, Integer> viewCartUser() {
        Map<String, Integer> getMapUser = gioHangService.getCartUser();
        for (Map.Entry<String, Integer> cartUser : getMapUser.entrySet()) {
            danhSachGH = new HashMap<>();
            ChiTietSP chiTietSP = chiTietSanPhamService.getOne(cartUser.getKey());
            danhSachGH.put(chiTietSP, cartUser.getValue());
        }
        return danhSachGH;
    }

    @PostMapping("/user/add/{id}")
    public Map<ChiTietSP, Integer> add(@PathVariable(name = "id", required = false) String id) {
        gioHangService.addCartUsee(id, 1);
        return viewCartUser();
    }

    @GetMapping("/client/hien-thi")
    public ResponseEntity<List<GioHangChiTiet>> getSanPhamTrongGio(HttpServletRequest request) {
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            GioHang gioHang = gioHangService.findGioHangByAccount(account);
            List<GioHangChiTiet> gioHangChiTietList = gioHangChiTietService.findGioHangChiTietByGioHang(gioHang);
            return ResponseEntity.ok(gioHangChiTietList);
        }else {
            return ResponseEntity.noContent().build();
        }

    }
}
