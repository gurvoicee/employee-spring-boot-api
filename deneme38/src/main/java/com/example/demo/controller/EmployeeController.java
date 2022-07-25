package com.example.demo.controller;

import com.example.demo.assembler.EmployeeAssembler;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
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
    EmployeeService employeeService;
    @GetMapping("/{id}")
    public EntityModel<Employee> getOne(@PathVariable Long id){
        return employeeService.getOne(id);
    }
    @GetMapping
    public CollectionModel<EntityModel<Employee>> getAll(){
        return employeeService.getall();
    }
    @PostMapping
    public ResponseEntity<EntityModel<Employee>> create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
            return employeeService.delete(id);
        }

}
