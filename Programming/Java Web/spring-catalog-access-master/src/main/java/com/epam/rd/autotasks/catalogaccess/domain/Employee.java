package com.epam.rd.autotasks.catalogaccess.domain;

public class Employee {
    private final long id;
    private final String name;
    private final Position position;

    public Employee(final long id, final String name, final Position position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public long getId() {
        return id;
    }
}
