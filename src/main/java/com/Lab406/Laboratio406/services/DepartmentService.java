package com.Lab406.Laboratio406.services;

import com.Lab406.Laboratio406.model.Department;
import com.Lab406.Laboratio406.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;


    public List<Department> getAllDepartments (){
        return departmentRepository.findAll();
    }

    //6 Create a route to add a new department
    public Department createDepartment(Department department) {
        if(department.getName() == null || department.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department name is required");
        }
        return departmentRepository.save(department);
    }
    //7.-Create a route to delete a department
    public Department deleteDepartment(Integer departmentID) {

        Department department = departmentRepository.findById(departmentID).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Deparment with id " + departmentID + " not found"));

        if(departmentRepository.findById(departmentID).isPresent()){
            departmentRepository.delete(department);
        }
        return department;
    }

}
