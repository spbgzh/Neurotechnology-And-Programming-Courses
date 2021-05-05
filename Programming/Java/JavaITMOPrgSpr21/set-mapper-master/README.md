# Employees. Row Mapper.

## Description 
Implement [`com.epam.rd.autocode.SetMapperFactory.employeesSetMapper`](src/main/java/com/epam/rd/autocode/SetMapperFactory.java) method.

It should return a SetMapper instance that maps whole result set to a set of `Employee` instances.

It should perform cursor moving in order to get all Employee instances.
Consider result set to be fully scrollable (back and forward, begin, end, etc.).
If an Employee has a Manager it should contain it as Employee instance as well.

You may refer to DDL in [`init-ddl.sql`](src/test/resources/init-ddl.sql).

You may not change classes in `com.epam.rd.autocode.domain` package.

 