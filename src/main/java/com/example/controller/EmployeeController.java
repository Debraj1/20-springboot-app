package com.example.controller;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository repository;
    @Autowired
    private KafkaService kafkaService;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return repository.add(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @GetMapping("/dog")
    public String getDogName(){
        return  "Lalu";
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation(){

        kafkaService.updateLocation("Kolkata");
        return new ResponseEntity<>(Map.of("message","Location Updated"), HttpStatus.OK);
    }

}
