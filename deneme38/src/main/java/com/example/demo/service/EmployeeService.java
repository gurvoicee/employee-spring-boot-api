package com.example.demo.service;

import com.example.demo.assembler.EmployeeAssembler;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeAssembler assembler;
    public CollectionModel<EntityModel<Employee>> getall(){

        List<Employee> employeeList = employeeRepository.findAll();

        return assembler.toCollectionModel(employeeList);
    }

    public EntityModel<Employee> getOne(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return assembler.toModel(employee);
    }
    public ResponseEntity<EntityModel<Employee>> create(Employee employee){
        try {
            employeeRepository.save(employee);
            return new ResponseEntity<>(assembler.toModel(employee), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Void> delete(Long id){
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException());
            employeeRepository.delete(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
