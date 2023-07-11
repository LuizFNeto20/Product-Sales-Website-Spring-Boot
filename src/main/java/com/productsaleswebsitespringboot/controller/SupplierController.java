package com.productsaleswebsitespringboot.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.productsaleswebsitespringboot.model.Supplier;
import com.productsaleswebsitespringboot.service.SupplierService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/new")
    public String newSupplier(Model model) {
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("isNewSupplier", true);
        return "/auth/admin/forms/SupplierForm";
    }

    @PostMapping("/save")
    public String saveSupplier(@Valid Supplier supplier, BindingResult result,
            RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            return "/auth/admin/forms/SupplierForm";
        }

        supplierService.saveSupplier(supplier);

        attributes.addFlashAttribute("mensagem", "saved supplier");
        return "redirect:/supplier/new";
    }

    @RequestMapping("/admin/list")
    public String list(Model model) {
        List<Supplier> suppliers = supplierService.getAllSuppliers(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("suppliers", suppliers);
        return "/auth/admin/listings/SupplierList";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteSuplier(Model model, @PathVariable("id") long id) {
        supplierService.deleteSupplier(id);
        return "redirect:/supplier/admin/list";
    }

    @GetMapping("/admin/edit/{id}")
    public String editSupplier(Model model, @PathVariable("id") long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        model.addAttribute("supplier", supplier);
        model.addAttribute("isNewSupplier", false);
        return "/auth/admin/forms/SupplierForm";
    }

    @PostMapping("/admin/update/{id}")
    public String updateSupplier(@Valid Supplier supplier, BindingResult result,
            @PathVariable("id") long id) {

        if (result.hasErrors()) {
            supplier.setId(id);
            return "/auth/admin/forms/SupplierForm";
        }

        supplierService.updateSupplier(supplier);

        return "redirect:/supplier/admin/list";
    }
}
