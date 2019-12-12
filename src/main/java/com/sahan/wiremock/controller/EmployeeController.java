package com.sahan.wiremock.controller;

import com.sahan.wiremock.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/employeeController")
public class EmployeeController {
    private final String APPLICATION_JSON = "application/json";

    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setFirstName("Sahan");
        emp1.setLastName("Ekanayake");
        emp1.setEmail("sahan@gmail.com");

        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setFirstName("Nisal");
        emp2.setLastName("Sanjana");
        emp2.setEmail("nisal@gmail.com");

        Employee emp3 = new Employee();
        emp3.setId(3);
        emp3.setFirstName("Kanchana");
        emp3.setLastName("Upulani");
        emp3.setEmail("kanchana@gmail.com");

        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);

        return employees;
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = {APPLICATION_JSON})
    public ResponseEntity getEmployeeById(@PathVariable("id") int id) {
        if (id != 0) {
            Employee employee = new Employee();
            employee.setId(1);
            employee.setFirstName("Sahan");
            employee.setLastName("Ekanayake");
            employee.setEmail("sahan@gmail.com");
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
