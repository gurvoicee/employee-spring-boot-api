package com.example.demo.assembler;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {
    @Override
    public EntityModel<Employee> toModel(Employee entity) {
        EntityModel<Employee> entityModel = EntityModel.of(entity);
        entityModel.add(linkTo(methodOn(EmployeeController.class).getOne(entity.getId())).withRel("self"));
        entityModel.add(linkTo(methodOn(EmployeeController.class).getAll()).withRel("all employees"));
        entityModel.add(linkTo(methodOn(EmployeeController.class).delete(entity.getId())).withRel("delete"));
        return entityModel;
    }

    @Override
    public CollectionModel<EntityModel<Employee>> toCollectionModel(Iterable<? extends Employee> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
