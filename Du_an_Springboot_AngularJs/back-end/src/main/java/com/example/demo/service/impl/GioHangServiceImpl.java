package com.example.demo.service.impl;

import com.example.demo.entity.Account;
import com.example.demo.entity.GioHang;
import com.example.demo.repository.IGioHangReposiotry;
import com.example.demo.service.IGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GioHangServiceImpl  implements IGioHangService {

    @Autowired
    IGioHangReposiotry gioHangReposiotry;

    private final Map<String,Integer> sanPhamTrongGioUser = new HashMap<>();

    @Override
    public Map<String, Integer> getCartUser() {
        return sanPhamTrongGioUser;
    }

    @Override
    public void addCartUsee(String value, Integer soLuongChoVao) {
            if(sanPhamTrongGioUser.containsKey(value)){
                int soLuongHienCo = sanPhamTrongGioUser.get(value);
                sanPhamTrongGioUser.put(value,soLuongHienCo+soLuongChoVao);
            }else {
                sanPhamTrongGioUser.put(value,soLuongChoVao);
            }
    }

    @Override
    public GioHang findGioHangByAccount(Account account) {
        return gioHangReposiotry.findGioHangByAccount(account);
    }
}
