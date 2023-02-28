package com.Lab406.Laboratio406.repositories;

import com.Lab406.Laboratio406.dto.ProductDTO;
import com.Lab406.Laboratio406.model.Department;
import com.Lab406.Laboratio406.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product,Integer> {


    List<Product> findByName (String name);
    List<Product> findByDepartmentId (Integer departmentId);
    List<Product> findAll();



}
