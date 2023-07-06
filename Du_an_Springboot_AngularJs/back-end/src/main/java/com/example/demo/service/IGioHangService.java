package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.GioHang;

import java.util.Map;

public interface IGioHangService {

    Map<String,Integer> getCartUser();

    void addCartUsee(String value, Integer soLuong);

    GioHang findGioHangByAccount(Account account);
}
