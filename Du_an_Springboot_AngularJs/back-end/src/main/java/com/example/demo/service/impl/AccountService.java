package com.example.demo.service.impl;

import com.example.demo.entity.Account;
import com.example.demo.repository.IAcountRepository;
import com.example.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    IAcountRepository acountRepository;

    @Override
    public Account findByEmailAndAndMatKhau(String email, String pass) {
        return acountRepository.findByEmailAndAndMatKhau(email,pass);
    }
}
