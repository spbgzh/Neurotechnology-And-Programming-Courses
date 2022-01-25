package com.epam.rd.autotasks.catalogaccess;

import com.epam.rd.autotasks.catalogaccess.domain.Employee;
import com.epam.rd.autotasks.catalogaccess.domain.Position;
import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/salaries")
public class SalaryController {

    @GetMapping
    public List<BigDecimal> getSalaries() {
        return ImmutableList.of(BigDecimal.ONE, BigDecimal.ZERO);
    }

    @GetMapping(value = "/my")
    public BigDecimal mySalary() {
        return BigDecimal.ZERO;
    }

}