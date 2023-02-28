package com.Lab406.Laboratio406.repositories;

import com.Lab406.Laboratio406.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    Department createDepartment(Department department);
}
