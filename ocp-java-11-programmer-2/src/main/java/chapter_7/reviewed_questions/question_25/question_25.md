## Which of the following properties of concurrency are true? (Choose all that apply.)

A. By itself, concurrency does not guarantee which task will be completed first. <br>
B. Concurrency always improves the performance of an application.   <br>
C. A computer with a single-processor CPU does not benefit from concurrency.    <br>
D. Applications with many resource-heavy tasks tend to benefit more from concurrency than one with CPU-intensive tasks. <br>
E. Concurrency tasks do not share the same memory.  <br>

Correct Answer: A, D

- By itself, concurrency does not guarantee which task will be completed first, so option A is incorrect.
- Furthermore, applications with numerous resources requests will often be stuck waiting for a resource, which allows
  other tasks to run.
- Therefore, they tend to benefit more from concurrency than CPU-intensive tasks, so option D is also correct.
- Option B is incorrect because concurrency may in fact make an application slower if it is truly single-threaded in nature.
- Keep in mind that there is a cost associated with allocating additional memory and CPU time to manage the concurrent process.
- Option C is incorrect because single-processor CPUs have been benefiting from concurrency for decades.
- Finally, option E is incorrect; there are numerous examples in this chapter of concurrency tasks sharing memory.