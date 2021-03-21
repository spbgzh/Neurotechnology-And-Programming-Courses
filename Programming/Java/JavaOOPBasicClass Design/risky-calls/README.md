# Risky calls

## Description
Handle calls to risky methods that can throw a lot types of exceptions.\
Some exceptions should be handled, some other - wrapped and rethrowed, some should just be not handled.

### Risky shot
Handle a risky method call in handleShot method in the [RiskyShot](src/main/java/com/efimchick/tasks/risky/RiskyShot.java) class.\
**handleShot() method heading may not have `throws` section** 

| What is thrown | How to handle |
| --- | --- |
| IOException | Wrap in an IllegalArgumentException with a message "File error" and throw it |
| FileNotFoundException | Wrap in an IllegalArgumentException with a message "File is missing" and throw it | 
| ArithmeticException or NumberFormatException | Retry call to the risky method increasing by 1 and 2 correspondingly the value of `input` parameter |
| UnsupportedOperationException | Should not be transformed |
| Any other Exception |  Wrap in an RuntimeException and throw it |

NullPointerException is not welcome. It should not be caught. I should not appear as well.

*Hint: you may add methods to the RiskyShot class*

### Risky resource
Handle a risky method call in handleCarelessConsuming method in the [RiskyResource](src/main/java/com/efimchick/tasks/risky/RiskyResource.java) class.\
No special error handling is required, just do not let any Exception get out of the method scope.
Important part here is resource - it should be closed in any case.

