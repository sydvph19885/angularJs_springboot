package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAcountRepository extends JpaRepository<Account,String> {

    Account findByEmailAndAndMatKhau(String email,String pass);
}
