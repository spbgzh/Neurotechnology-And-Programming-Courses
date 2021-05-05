package com.epam.rd.autocode;

import java.sql.ResultSet;

public interface SetMapper<T> {
    T mapSet(ResultSet resultSet);
}
