package com.Lab406.Laboratio406.controllers;

import com.Lab406.Laboratio406.model.Department;
import com.Lab406.Laboratio406.model.Product;
import com.Lab406.Laboratio406.repositories.ProductRepository;
import com.Lab406.Laboratio406.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/departments")
    @ResponseStatus(HttpStatus.OK)
    public List<Department> GetAllDepartments (){
        return departmentService.getAllDepartments();
    }


    @PostMapping("/department-add")
    @ResponseStatus(HttpStatus.CREATED)
    public Department createNew (@Valid @RequestBody Department department){
        return departmentService.createDepartment(department);
    }

    @DeleteMapping("/department-delete/{departmentID}")
    public Department delete(@PathVariable Integer departmentID) {
        return departmentService.deleteDepartment(departmentID);
    }
}


