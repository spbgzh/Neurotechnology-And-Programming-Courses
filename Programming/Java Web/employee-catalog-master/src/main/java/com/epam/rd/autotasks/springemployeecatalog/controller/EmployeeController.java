package com.epam.rd.autotasks.springemployeecatalog.controller;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.domain.Paging;
import com.epam.rd.autotasks.springemployeecatalog.factory.*;
import com.epam.rd.autotasks.springemployeecatalog.service.implemployeeservice.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final String ALL = "all";
    private static final String MANAGER = "manager";
    private static final String DEPARTMENT = "department";

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    @GetMapping("")
    public List<Employee> getAllEmployees(@RequestParam(value = "page", required = false) String page,
                                          @RequestParam(value = "size", required = false) String size,
                                          @RequestParam(value = "sort", required = false) String sort) {
        if (page == null) {
            return employeeService.getAll();
        }
        Paging paging = new Paging(Integer.valueOf(page), Integer.valueOf(size));

        ISortModeFactory sortFactory = createSortModeByEntity(ALL);
        ISortMode sortMode = sortFactory.createSortMode();
        return sortMode.getSort(sort, paging, null, null);
    }


    @GetMapping("/{employee_id}")
    public Employee getEmployeeById(@PathVariable("employee_id") Long employeeId,
                                    @RequestParam(value = "full_chain", defaultValue = "false") Boolean isFullChain) {
        if (isFullChain) {
            return employeeService.getByIdWithFullChain(employeeId);
        } else {
            return employeeService.getById(employeeId);
        }
    }

    @GetMapping("/by_manager/{manager_id}")
    public List<Employee> getEmployeesByManager(@PathVariable("manager_id") Long managerId,
                                                @RequestParam("page") String page,
                                                @RequestParam("size") String size,
                                                @RequestParam("sort") String sort) {
        Paging paging = new Paging(Integer.valueOf(page), Integer.valueOf(size));

        ISortModeFactory sortFactory = createSortModeByEntity(MANAGER);
        ISortMode sortMode = sortFactory.createSortMode();
        return sortMode.getSort(sort, paging, managerId, null);
    }

    @GetMapping("/by_department/{department}")
    public List<Employee> getEmployeesByDepartment(@PathVariable("department") String department,
                                                   @RequestParam("page") String page,
                                                   @RequestParam("size") String size,
                                                   @RequestParam("sort") String sort) {
        Paging paging = new Paging(Integer.valueOf(page), Integer.valueOf(size));

        ISortModeFactory sortFactory = createSortModeByEntity(DEPARTMENT);
        ISortMode sortMode = sortFactory.createSortMode();
        return sortMode.getSort(sort, paging, null, department);
    }
    private ISortModeFactory createSortModeByEntity(String entity) {
        switch (entity) {
            case "all":
                return new SortModeFactoryAll();
            case "manager":
                return new SortModeFactoryManager();
            case "department":
                return new SortModeFactoryDepartment();
            default:
                throw new IllegalArgumentException("A non-existent entity was obtained!");
        }
    }
}
