## Java - Reentrant Locks
---
#### What is a Reentrant Lock?

* Extends the basic/intrinsic behaviour of typical Lock (of Java)
* Acquiring an ownership:
    * It is owned the thread successfully locking it “last”, but yet “unlocking” it.
    * A thread invoking lock, returns successfully with the lock, when none of the other threads have been holding onto the lock.
    * The method will return immediately, if the thread has already been holding on to the lock.
        * This can be checked by methods `isHelpByCurrentThread()` or `getHoldCount()`.


* The constructor accepts a boolean parameter for fairness
    * When set to true (false being default), locks favour granting access to the longest waiting thread.
    * If false (default), no order can be guaranteed.


* Programs with fair locks accessed by many threads may display overall low throughput (often much slower), but have similar variances in times to obtain locks and guarantee lack of starvation.


* Fairness of locks does not guarantee fairness of thread scheduling. Thus, one of the many threads using a fair lock may obtain it multiple times in succession while other active threads are not progressing and not currently holding on the lock. 


* Also, note that the untimed tryLock method does not honour the fairness setting. It will succeed if the lock is available even other threads are waiting.


* It is recommended practice to always immediately follow a call to lock with a try block, most typically in a before/after construction such as:

    
        
        class X {
            private final ReentrantLock lock = new ReentrantLock();
            
            // ...


            public void m() {
                lock.lock();  // block until condition holds
                try {
                // ... method body
                } finally {
                lock.unlock()
                }
            }
        }
        