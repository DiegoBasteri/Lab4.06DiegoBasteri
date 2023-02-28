package com.Lab406.Laboratio406.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;


//1. Create a database with the following tables and add some additional data:
@Entity
public class Department {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer departmentId;

    private String name;

    @OneToMany(mappedBy = "RIdDepartment",fetch = FetchType.EAGER)
    @JsonIgnore
    List<Product> productList;

    public Department() {
    }

    public Department(String name) {
        this.name = name;

    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentId, that.departmentId) && Objects.equals(name, that.name) && Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, name, productList);
    }
}
