package com.productsaleswebsitespringboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.UserRole;
import com.productsaleswebsitespringboot.model.Users;
import com.productsaleswebsitespringboot.repository.UserRepository;

@Service
public class UserServiceImplements implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private BCryptPasswordEncoder cryptography;

    @Override
    public void deleteUser(Long id) {
        Users user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public Users getUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException(id + " não encontrado");
        }
    }

    @Override
    public Users saveUser(Users user) {
        UserRole role = userRoleService.getRole("USER");
        List<UserRole> roles = new ArrayList<>();
        roles.add(role);
        user.setUserRoles(roles);

        String encryptedPassword = cryptography.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    @Override
    public void updateUser(Users user) {
        userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers(Sort sort) {
        List<Users> userList = userRepository.findAll(sort);
        return userList;
    }

    @Override
    public Users getUserByLogin(String login) {
        Users user = userRepository.findByLogin(login);
        return user;
    }

    @Override
    public void assignUserRole(Long id, int[] idRoles) {
        List<UserRole> roles = new ArrayList<>();
        for (int i = 0; i < idRoles.length; i++) {
            long idRole = idRoles[i];
            UserRole role = userRoleService.getRoleById(idRole);
            roles.add(role);
        }
        
        Users user = getUserById(id);
        user.setUserRoles(roles);
        updateUser(user);
    }
}
