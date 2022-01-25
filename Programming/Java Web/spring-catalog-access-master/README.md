# Spring Catalog Access

You must specify spring security config to manage role-based access to the given web application controllers.

There are three roles:
- MANAGER
- EMPLOYEE
- CUSTOMER

There are three controller and related paths:
- [EmployeesController](src/main/java/com/epam/rd/autotasks/catalogaccess/EmployeesController.java)
    : `/employees`
    - GET requests must be available to managers and employees,
    - POST requests must be available to managers only,
- [SalaryController](src/main/java/com/epam/rd/autotasks/catalogaccess/SalaryController.java)
  : `/salaries`
    - Request to all salaries (`/salaries`) must be available to managers only,
    - Request to the salary of the employee (`/salaries`) must be available to any employee or manager,
- [CatalogController](src/main/java/com/epam/rd/autotasks/catalogaccess/CatalogController.java)
  : `/catalog`
    - Requests to catalog must be available to any manager, employee or customer.

Configure Spring Security in [WebSecurityConfig](src/main/java/com/epam/rd/autotasks/catalogaccess/WebSecurityConfig.java).\
There is no need to edit controllers.

*Note: Don't forget to disable CSRF protection.*