package com.epam.rd.autocode;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T mapRow(ResultSet resultSet);
}
