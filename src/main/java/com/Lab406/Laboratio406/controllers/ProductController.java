package com.Lab406.Laboratio406.controllers;

import com.Lab406.Laboratio406.dto.ProductDTO;
import com.Lab406.Laboratio406.model.Department;
import com.Lab406.Laboratio406.model.Product;
import com.Lab406.Laboratio406.repositories.DepartmentRepository;
import com.Lab406.Laboratio406.repositories.ProductRepository;
import com.Lab406.Laboratio406.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> productList (){
        return productService.productList();
    }

    @PostMapping("/product/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewProduct(@RequestBody ProductDTO productDTO){
        return productService.createNewProduct(productDTO);

    }

    @PatchMapping("/product/decrementquantity")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product updateQuantity(@RequestParam Integer id , @RequestParam Integer amount){
        return productService.decrementQuantity(id,amount);
    }


    @GetMapping("/departments/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductsByDepartment(@RequestParam Integer departmentId){
        return productService.getProductsByDepartment(departmentId);
    }

    @DeleteMapping("/product-delete/{id}")
    public List<Product> delete(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}
