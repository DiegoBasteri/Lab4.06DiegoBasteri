package com.Lab406.Laboratio406.services;

import com.Lab406.Laboratio406.dto.ProductDTO;
import com.Lab406.Laboratio406.model.Department;
import com.Lab406.Laboratio406.model.Product;
import com.Lab406.Laboratio406.repositories.DepartmentRepository;
import com.Lab406.Laboratio406.repositories.ProductRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Product> productList (){
        return productRepository.findAll();
    }


    //2. Create a route for adding new products (validate the payload format)


    public Product createNewProduct (ProductDTO productDTO){
        Department department = departmentRepository.findById(productDTO.getDepartmentId()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.BAD_REQUEST,"Department doesn't exist"));
        Product product = new Product(department,productDTO.getName(),productDTO.getQuantity());
                return  productRepository.save(product);
    }
//3. Create a route for decrementing the quantity of a product by an amount specified in a parameter.
// Throw a custom error and respond with an appropriate response code if the specified amount is less than the quantity of the product in stock.

    public Product decrementQuantity (Integer id,Integer amount){
        Product product = productRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Product related to ID doesn't exist"));
        if (amount > product.getQuantity()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Amount is bigger than current quantity");
        product.setQuantity(product.getQuantity()- amount);
                return productRepository.save(product);
    }


//4. Create a route to get all products by department. Return all products if the department param is empty/null.
    public List<Product> getProductsByDepartment(Integer departmentId){
        if(departmentId == null) {
            return productRepository.findAll();
        } else {
            return productRepository.findByDepartmentId(departmentId);
        }
    }

    //7. Create a DELETE route to delete products from the product list by id.
    public List<Product> deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Deparment with id " + id+ " not found"));

        if(productRepository.findById(id).isPresent()){
            productRepository.delete(product);
        }
        return productRepository.findAll();
    }


}
