# Employees. Service.

## Description 
Implement [`com.epam.rd.autocode.service.ServiceFactory`](src/main/java/com/epam/rd/autocode/service/ServiceFactory.java) method.

It should return an Employee service instance.


You may refer to DDL in [`init-ddl.sql`](src/main/resources/init-ddl.sql).

You may not change classes in `com.epam.rd.autocode.domain` package.

Services often performs as a layer between DAO and controllers as it is suggested by classical 3-layer architecture design.
Although main function of a Service is considered to be a provision of business logic so mature architecture approaches like Clean Architecture, DDD specifies that a Service should not depend on persistence layer. 
So one may consider an Employee Service you need to implement to be really a Repository approach. 

Anyway, you should implement EmployeeService interface.

**Important**: Usually you have to provide an employee as an object of Employee class, having injected Department object and injected Employee object referencing to his manager.
Object of a manager should be having injected Department as well, but his own manager should not be injected.
Trying to show it in graphical way:
```
employee
    |-department
    |-manager
        |-department
```

Though, implementation of `getWithDepartmentAndFullManagerChain` method requires extracting of an employee with full management chain 
meaning that it should have a manager who should have a manager who should have a manager and so on up to top manager.
So, it will look like:
```
employee
    |-department
    |-manager
        |-department
        |-manager
            |-department
            |-manager
                |-department
                |-manager
                    |-department
                    |-manager
                        ...
```

You may not change classes in `com.epam.rd.autocode.domain` package.

 