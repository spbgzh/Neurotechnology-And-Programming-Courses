# Expression Calculator

Create a servlet, serving expression value calculating.

It must serve `GET` requests.

Each request has an `expression` parameter containing expression to evaluate.
Expression may contain integer numbers, basic operators like `+`, `-`, `*`, `/`, `(`, `)`, variable names (latin lowercase single characters), spaces.

Each variable value is served as additional request parameter with corresponding name and having value of integer number or name of another variable.
In latter case use the same value as variable having corresponding name.

Register servlet to serve at `/calc` context path.

All calculations must be integer. 

Refer to unit tests to get details of other implementation restrictions. 
 
