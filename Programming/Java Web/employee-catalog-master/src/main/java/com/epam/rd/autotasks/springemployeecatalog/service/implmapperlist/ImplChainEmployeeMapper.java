package com.epam.rd.autotasks.springemployeecatalog.service.implmapperlist;

import com.epam.rd.autotasks.springemployeecatalog.domain.Employee;
import com.epam.rd.autotasks.springemployeecatalog.service.Mapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplChainEmployeeMapper implements Mapper {

    private MapperFactory factory = new MapperFactory();

    @Override
    public List<Employee> mapping(ResultSet resultSet) {
        Map<Long, Long> managerMap = new HashMap<>();
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> resultEmployeeList = new ArrayList<>();
        factory.createEmployeeList(resultSet, employeeList, managerMap);

        updateToChain(resultEmployeeList, employeeList, managerMap);
        return resultEmployeeList;
    }

    private void updateToChain(List<Employee> resultEmployeeList, List<Employee> employeeList, Map<Long, Long> managerMap) {
        while (true) {
            factory.createChainEmployees(resultEmployeeList, employeeList, managerMap);
            if (employeeList.equals(resultEmployeeList)) {
                break;
            }
            employeeList.clear();
            employeeList.addAll(resultEmployeeList);
            resultEmployeeList.clear();
        }
    }
}
