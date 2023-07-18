package com.productsaleswebsitespringboot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.productsaleswebsitespringboot.model.Users;
import com.productsaleswebsitespringboot.service.UserRoleService;
import com.productsaleswebsitespringboot.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private static String imagePath = "/Users/luizf/Desktop/Projetos/Spring Boot/Product-Sales-Website-Spring-Boot/src/main/resources/templates/auth/admin/image/users/";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new Users());
        return "/auth/user/UserForm";
    }

    @GetMapping("/showImage/{image}")
    @ResponseBody
    public byte[] showImage(@PathVariable("image") String image) throws IOException {
        File fileImage = new File(imagePath + image);
        if (image != null) {
            return Files.readAllBytes(fileImage.toPath());
        }

        return null;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid Users user, BindingResult result,
            RedirectAttributes attributes, Model model, @RequestParam("file") MultipartFile file) {

        if (result.hasErrors()) {
            return "/auth/user/UserForm";
        }

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();

                String ImageName = String.valueOf(user.getId()) + file.getOriginalFilename();

                Path path = Paths.get(imagePath + ImageName);
                Files.write(path, bytes);

                user.setImage(ImageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Users usr = userService.getUserByLogin(user.getLogin());
        if (usr != null) {
            model.addAttribute("emailExists", "Email já existe");
            return "/auth/user/UserForm";
        }

        userService.saveUser(user);

        attributes.addFlashAttribute("mensagem", "saved user");
        return "redirect:/user/new";
    }

    @RequestMapping("/admin/list")
    public String list(Model model) {
        List<Users> users = userService.getAllUsers(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("users", users);
        return "/auth/admin/listings/UserList";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/user/admin/list";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(Model model, @PathVariable("id") long id) {
        Users user = userService.getUserById(id);
        model.addAttribute("user", user);

        model.addAttribute("roles", userRoleService.getAllRoles());

        return "/auth/admin/forms/EditUserAdmin";
    }

    @PostMapping("/admin/update/{id}")
    public String updateUser(@PathVariable("id") long idUser,
            @RequestParam(value = "idRoles", required = false) int[] idRoles,
            Users user, RedirectAttributes attributes) {

        if (idRoles == null) {
            user.setId(idUser);
            attributes.addFlashAttribute("mensagem", "");
            return "redirect:/usuario/admin/listar";
        } else {
            userService.assignUserRole(idUser, idRoles);
        }

        return "redirect:/user/admin/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        Users user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/auth/user/EditUserForm";
    }
}
