# Streams. Pipelines.

## Description

Implement [Collecting](src/main/java/com/efimchick/ifmo/Collecting.java) methods.

### Int Streams
| Method | Description |
| --- | --- |
| *sum* | Return sum of stream elements |
| *production* | Return production of stream elements |
| *oddSum* | Return sum of odd stream elements |
| *sumByRemainder* | Return sums of elements grouped by a remainder of division by the give divisor|

### Course Results Streams
| Method | Description |
| --- | --- |
| *totalScores* | Return map of persons to their total scores (as average). | 
| *averageTotalScore* | Return average result of all persons <br/>(be sure to consider all tasks of the course and not just those that were completed by a particular student) |
| *averageScoresPerTask* | Return map of average results per tasks |
| *defineMarks* | Return map of person to their marks:<br/> - *A* - total score in (90; 100]<br/> - *B* - total score in [83; 90]<br/> - *C* - total score in [75; 83)<br/> - *D* - total score in [68; 75)<br/> - *E* - total score in [60; 68)<br/> - *F* - total score < 60<br/> |
| *easiestTask* | Return the task with highest average score |
| *printableStringCollector* | Return a collector that forms a special printable String as described below. |

**Important**: Count average values including tasks that a student did not try to solve (with 0 score). See the Printable String Spec example.

#### Printable String spec
A multiline String containing a formatted table of all results and total values and marks.\
Headers are required.\
Column width depends on values lenghts.\
Sort rows by students last names.\
Sort columns by task name.\
Double values formatted to 2 digits after the point.\
Average mark is simple a mark of average total score.\
Pay attention to alignment of different columns.
 
 
Example:

    Student        | Phalanxing | Shieldwalling | Tercioing | Wedging | Total | Mark |
    Eco Betty      |          0 |            83 |        89 |      59 | 57.75 |    F |
    Lodbrok Johnny |         61 |            92 |        67 |       0 | 55.00 |    F |
    Paige Umberto  |         75 |            94 |         0 |      52 | 55.25 |    F |
    Average        |      45.33 |         89.67 |     52.00 |   37.00 | 56.00 |    F |
    
    
## Extra challenge
Try to use no cycles and no `if` statements.
