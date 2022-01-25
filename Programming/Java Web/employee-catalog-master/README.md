# Spring Boot Employee Catalog

Create a webapp serving somewhat like a REST catalog of employees stored in embedded database.

Implement endpoints serving employees as JSON representations of `com.epam.rd.autotasks.springemployeecatalog.domain.*` classes.

Each employee record should include information of his department and his manager.
Manager's manager should be usually null. 

Endpoints (all serves GET requests):

* `/employees` - list all employees. Supports paging*.

* `/employees/{employee_id}` - single employee. 
If parameter named `full_chain` exists and is set to true then full manager chain should be written 
(include employee\`s manager, manager of manager, manager of manager of manager and so on up to the organization head)

* `/employees/by_manager/{managerId}` - list employees who subordinates to the manager. No transitivity. Supports paging*.

* `/employees/by_department/{departmentId or departmentName}` - list employees who is working in the department. Supports paging*.

\* Supports paging - means that you may manage what sublist of employees you want to get by three parameters:
* `page` - number of the page (starts with `0`)
* `size` - amount of entry per page
* `sort` - name of the field for sorting (single value from list \[`lastName`, `hired`, `position`, `salary`\], order is ascending)

*Reminder:*
- You may not change domain classes
- You may not change sql scripts
- You should serve employees from the database without any in-memory storage