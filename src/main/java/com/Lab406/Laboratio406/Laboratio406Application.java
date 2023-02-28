package com.Lab406.Laboratio406;

import com.Lab406.Laboratio406.model.Department;
import com.Lab406.Laboratio406.model.Product;
import com.Lab406.Laboratio406.repositories.DepartmentRepository;
import com.Lab406.Laboratio406.repositories.ProductRepository;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Laboratio406Application implements CommandLineRunner {

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Laboratio406Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {


//Fill department table
		departmentRepository.saveAll (Arrays.asList(

				new Department ("Tools"),
				new Department ("Edible Plants"),
				new Department ("Non-edible Plants"),
				new Department ("Edible Seeds"),
				new Department ("Non-edible Seeds"),
				new Department ("Miscellaneous")
				));

//Fill Product table
		productRepository.saveAll(Arrays.asList(
				new Product(departmentRepository.findById(1).get(),"small shovel",50),
				new Product(departmentRepository.findById(1).get(),"large shovel",150),
				new Product(departmentRepository.findById(2).get(),"apple tree sapling",10),
				new Product(departmentRepository.findById(4).get(),"assorted root vegetable seed packet",2000),
				new Product(departmentRepository.findById(5).get(),"geranium seed packet",1000),
				new Product(departmentRepository.findById(2).get(),"sprouted carrots",200),
				new Product(departmentRepository.findById(6).get(),"large brim gardening hat",25)
));


	}
}
