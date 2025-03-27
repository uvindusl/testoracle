package com.example.test_oracle.Controller;

import com.example.test_oracle.Entity.EmployeeEntity;
import com.example.test_oracle.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{employeeId}")
    public EmployeeEntity getEmployeeDetails(@PathVariable String employeeId){
        return employeeService.getEmployeeDetails(employeeId);
    }
}
