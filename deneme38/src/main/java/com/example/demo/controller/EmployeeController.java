package com.example.demo.controller;

import com.example.demo.assembler.EmployeeAssembler;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository repository;
    @Autowired
    EmployeeAssembler assembler;
    @GetMapping("/{id}")
    public EntityModel<Employee> getOne(@PathVariable Long id){
        Employee employee = repository.findById(id).orElseThrow(()->new RuntimeException());
        return assembler.toModel(employee);
    }
    @GetMapping
    public CollectionModel<EntityModel<Employee>> getAll(){
        List<Employee> employees = repository.findAll();
        return assembler.toCollectionModel(employees);
    }
    @PostMapping
    public ResponseEntity<EntityModel> create(@RequestBody Employee employee){
        try {
            repository.save(employee);
            return new ResponseEntity(assembler.toModel(employee),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            Employee employee = repository.findById(id).orElseThrow(()->new RuntimeException());
            repository.delete(employee);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
