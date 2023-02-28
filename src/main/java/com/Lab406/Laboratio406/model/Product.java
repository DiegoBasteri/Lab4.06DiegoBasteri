package com.Lab406.Laboratio406.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

//1. Create a database with the following tables and add some additional data:

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department RIdDepartment;

    private String name;

    private Integer quantity;

    public Product() {
    }

    public Product(Department RIdDepartment, String name, Integer quantity) {
        this.RIdDepartment = RIdDepartment;
        this.name = name;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Department getRIdDepartment() {
        return RIdDepartment;
    }

    public void setRIdDepartment(Department RIdDepartment) {
        this.RIdDepartment = RIdDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(RIdDepartment, product.RIdDepartment) && Objects.equals(name, product.name) && Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, RIdDepartment, name, quantity);
    }
}
