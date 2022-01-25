package com.epam.rd.autocode.domain;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Employee {
    private final BigInteger id;
    private final FullName fullName;
    private final Position position;
    private final LocalDate hired;
    private final BigDecimal salary;
    private final BigInteger managerId;
    private final BigInteger departmentId;

    @JsonCreator
    public Employee(@JsonProperty("id") final BigInteger id,
                    @JsonProperty("fullName") final FullName fullName,
                    @JsonProperty("position") final Position position,
                    @JsonProperty("hired") final LocalDate hired,
                    @JsonProperty("salary") final BigDecimal salary,
                    @JsonProperty("managerId") final BigInteger managerId,
                    @JsonProperty("departmentId") final BigInteger departmentId) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.hired = hired;
        this.salary = salary.setScale(5, RoundingMode.HALF_UP);
        this.managerId = managerId;
        this.departmentId = departmentId;
    }

    public BigInteger getId() {
        return id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public Position getPosition() {
        return position;
    }

    public LocalDate getHired() {
        return hired;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public BigInteger getManagerId() {
        return managerId;
    }

    public BigInteger getDepartmentId() {
        return departmentId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Employee employee = (Employee) o;
        return Objects.equal(id, employee.id) &&
                Objects.equal(fullName, employee.fullName) &&
                position == employee.position &&
                Objects.equal(hired, employee.hired) &&
                Objects.equal(salary, employee.salary) &&
                Objects.equal(managerId, employee.managerId) &&
                Objects.equal(departmentId, employee.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, fullName, position, hired, salary, managerId, departmentId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("fullName", fullName)
                .add("position", position)
                .add("hired", hired)
                .add("salary", salary)
                .add("managerId", managerId)
                .add("departmentId", departmentId)
                .toString();
    }

    public static class Parser {
        private static ObjectMapper mapper = new ObjectMapper();
        static {
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        }

        public static String toJson(Employee employee){
            try {
                final StringWriter writer = new StringWriter();
                mapper.writeValue(writer, employee);
                return writer.toString();
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        }

        public static Employee parseJson(String json){
            try {
                return mapper.readValue(json, Employee.class);
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        }
    }
}
