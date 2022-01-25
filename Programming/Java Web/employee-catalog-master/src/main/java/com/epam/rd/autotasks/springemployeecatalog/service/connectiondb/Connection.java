package com.epam.rd.autotasks.springemployeecatalog.service.connectiondb;

import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connection {

    private static final Connection instance = new Connection();
    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASS = "";

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public java.sql.Connection createConnectionDb()
            throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
