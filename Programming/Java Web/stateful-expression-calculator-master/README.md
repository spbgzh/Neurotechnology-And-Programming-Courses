# Servlets Stateful Expression Calculator

Create a webapp serving expression value calculating.

It must serve `PUT`, `GET`, `DELETE` requests and keep user sessions.

User can put expression to evaluate or variable values via `PUT` requests.

To set an expression user executes PUT request to `/calc/expression` URI providing expression as a request body.

To set a variable user executes PUT request to `/calc/<variable_name>` URI where `<variable_name>` is a variable name (latin lowercase single characters) and variable value is the request body.
 
User may update expression or variable value by executing another `PUT` request of the same format.\
When expression or variable is set for the first time, webapp should return `201` status code. Also, setting URI of created resource as a Location header value is welcome.\
When expression or variable is updated, webapp should return `200` status code.

Each variable has value of integer number or name of another variable.
In latter case use the same value as a variable with corresponding name.
If user has sent incorrect value (badly formatted) for expression or variable webapp should return `400` status code and reason of the error.
All variable values should be in `[-10000; 10000]` range. Exceeding values should lead to `403` status code.
User may unset equation or variable by executing `DELETE` request to corresponding resource URL.
Such requests should return `204` status codes.

User may get value of evaluated expression by execution `GET` request to `/calc/result` URI.\
In case, when expression is set, and it may be evaluated, webapp should return `200` status code and calculated integer value as a response body.\
In case, when expression may not be calculated due to lack of data, webapp should return `409` status code and reason of this error.  

All calculations must be integer.
Webapp must be implemented on the basis of http servlets and servlet.ExpressionServlet filters. 