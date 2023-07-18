package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Users;


public interface UserService {

    public void deleteUser(Long id);

    public Users getUserById(Long id);

    public Users getUserByLogin(String login);

    public Users saveUser(Users user);

    public void updateUser(Users user);

    public List<Users> getAllUsers(Sort sort);

    public void assignUserRole(Long id, int[] idRoles);
}
