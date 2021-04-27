# Thread Factoring 

## Description 
Implement [`com.epam.rd.autotasks.ThreadUnion`](src/main/java/com/epam/rd/autotasks/ThreadUnion.java) `getInstance` method.

It should return a `ThreadUnion` instance.

Thread Union is a named Thread factory that creates threads, monitors their execution results and allows their bunch shutdown.

Methods description:

| Method | Description |
| --- | --- |
| `Thread newThread (Runnable runnable)` | Creates and registers a new Thread. Name of the thread should be "&lt;_thread-union-name_&gt;-worker-_n_", where _n_ is a number of a thread. A ThreadUnion must monitor execution of a created thread - refer to `results()` method. | 
| `int totalSize()` | Total amount of threads created within a ThreadUnion. |
| `int activeSize()` | Total amount of currently active threads created within a ThreadUnion. |
| `void shutdown()` | Interrupts all created threads and prevents creating more threads via `newThread` method. |
| `boolean isShutdown()` | Returns true if shutdown was called. |
| `void awaitTermination()` | Blocks until all of created threads are finished. |
| `boolean isFinished()` | Checks if a ThreadUnion was shutdown and all of created threads has finished.|
| `List<FinishedThreadResult> results()` | Returns a list of results of finished threads. No results must be returned for threads that are not finished yet. A result must contain a thread name, a timestamp when it finished execution and a Throwable if it was thrown |

 Note, that your `ThreadUnion` implementation should be threadsafe and support concurrent thread generating.  