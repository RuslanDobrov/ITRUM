package ru.itrum.springData.task02.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itrum.springData.task02.models.Department;
import ru.itrum.springData.task02.models.Employee;
import ru.itrum.springData.task02.projection.EmployeeProjection;
import ru.itrum.springData.task02.services.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);

        return optionalEmployee.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/projection")
    public ResponseEntity<EmployeeProjection> getEmployeeProjection(@PathVariable Long id) {
        Optional<EmployeeProjection> optionalProjection = employeeService.findProjectedById(id);

        return optionalProjection.map(projection -> new ResponseEntity<>(projection, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            modelMapper.map(updatedEmployee, existingEmployee);
            existingEmployee.setId(id);
            if (updatedEmployee.getDepartment() != null) {
                Department existingDepartment = existingEmployee.getDepartment();
                modelMapper.map(existingDepartment, updatedEmployee.getDepartment());
                existingDepartment.setId(updatedEmployee.getDepartment().getId());
            }
            return new ResponseEntity<>(employeeService.save(existingEmployee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = employeeService.findById(id);

        if (optionalEmployee.isPresent()) {
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
