package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@SessionAttributes("account")
public class AccountRestController {

    @Autowired
    IAccountService accountService;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public ResponseEntity<Account> findByEmailAndAndMatKhau(@RequestParam(name = "email",required = false) String email, @RequestParam(name = "pass",required = false) String pass) {
        Account account = accountService.findByEmailAndAndMatKhau(email, pass);
        if (account != null) {
            session.setAttribute("account", account);
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
