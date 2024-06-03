## Which happens when a new task is submitted to an *ExecutorService*, in which there are no threads available?

A. The executor throws an exception when the task is submitted.
B. The executor discards the task without completing it.
C. The executor adds the task to an internal queue and completes when there is an available thread.
D. The thread submitting the task waits on the submitt call until a thread is available before continuing.
E. The executor creates a new temporary thread to complete the task.


## Correct Answer: C