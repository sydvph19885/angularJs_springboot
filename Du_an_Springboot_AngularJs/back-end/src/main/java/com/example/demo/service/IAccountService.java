package com.example.demo.service;

import com.example.demo.entity.Account;

public interface IAccountService {
    Account findByEmailAndAndMatKhau(String email, String pass);
}
