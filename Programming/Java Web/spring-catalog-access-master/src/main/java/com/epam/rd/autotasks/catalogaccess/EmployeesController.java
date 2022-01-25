package com.epam.rd.autotasks.catalogaccess;

import com.epam.rd.autotasks.catalogaccess.domain.Employee;
import com.epam.rd.autotasks.catalogaccess.domain.Position;
import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeesController {

    private List<Employee> employees = ImmutableList.of(
            new Employee(1, "John Smith", Position.ANALYST),
            new Employee(2, "Jurend Swenk", Position.SALESMAN),
            new Employee(3, "Armin von Langerd", Position.PRESIDENT)
    );


    @GetMapping
    public List<Employee> getEmployees() {
        return employees;
    }

    @GetMapping(value = "/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Employee putEmployee(@RequestParam("name") String name,
                                @RequestParam("position") Position position) {
        return new Employee(4, name, position);
    }


}