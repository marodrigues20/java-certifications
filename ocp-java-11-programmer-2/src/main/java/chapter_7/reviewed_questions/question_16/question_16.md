## Which statements about methods in *ReentrantLock* are correct?
## (Choose all that apply.)

A. The *lock()* method will attempt to acquire a lock without waiting indefinitely for it. <br>
B. The *testLock()* method will attempt to acquire a lock without waiting indefinitely for it. <br>
C. The *attemptLock() method will attempt to acquire a lock without waiting indefinitely for it. <br>
D. By default, a *ReentrantLock* fairly releases to each thread, in the order that it was requested. <br>
E. Calling the *unlock()* method once will release a resource so that other threads can obtain the lock. <br>
F. None of the above. <br>

Correct Answer: F

- The *lock()* method will wait indefinitely for a lock, so option A is incorrect.
- Options B and C are also incorrect, as the correct method name to attempt to acquire a lock is *tryLock()*.
- Option D is incorrect, as fairness is set to *false* by default and must be enabled by using an overloaded constructor.
- Finally, option E is incorrect because a thread that holds the lock may have called *lock()* or *tryLock()* multiple times.
- A thread needs to call *unlock()* once for each call to *lock()* and *tryLock()*.


