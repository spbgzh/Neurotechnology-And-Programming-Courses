# Spring Custom Scopes
There are two main common non-web scopes in Spring:
- **singleton**\
  Returns the same instance each time the bean is accessed.
- **prototype**\
  Returns a new instance each time the bean is accessed.
  
Create three custom scopes for spring beans to use in Java Configuration:
- **threeTimes**\
  Beans of this scope must return a new instance after accessing them three times.   
- **thread**\
  Beans of this scope must return an instance bound to a thread requesting a bean. In short, one instance per thread. 
- **justASecond**\
  Beans of this scope return an instance that will expire in one second.
  So, once the bean instance is created, it lives the next second, and context must return it each time the bean is accessed.
  When accessing the bean and the instance expired, the context must return a new instance that will be available next second.
  
Register scopes in the following config classes:
- [ThreeTimesScopeConfig](src/main/java/com/rd/epam/autotasks/scopes/config/ThreeTimesScopeConfig.java)
- [ThreadScopeConfig](src/main/java/com/rd/epam/autotasks/scopes/config/ThreadScopeConfig.java)
- [JustASecondScopeConfig](src/main/java/com/rd/epam/autotasks/scopes/config/JustASecondScopeConfig.java)

That classes are imported in test configuratons.
