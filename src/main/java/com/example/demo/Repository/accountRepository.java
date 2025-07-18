package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Account;

import jakarta.persistence.Id;


public interface accountRepository extends JpaRepository<Account, Integer> {

}
