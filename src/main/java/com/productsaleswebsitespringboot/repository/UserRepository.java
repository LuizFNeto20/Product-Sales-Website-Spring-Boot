package com.productsaleswebsitespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsaleswebsitespringboot.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
    Users findByLogin(String login);
}
