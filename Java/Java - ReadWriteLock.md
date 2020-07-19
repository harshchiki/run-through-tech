### Java - ReadWriteLock
----------------------
* *Interface*


* Maintains 2 pairs of locks
    * Read only operations
    * writing


* Locking Strategy:
    * Multiple readers can acquire the lock.
    * Only single writer is allowed
    * If a writer is holding the lock, neither reader, nor the writer is allowed.


* Boosts performance, as lock contention reduces if the thread intends to read only.
    * Whether or not a read-write lock will improve performance over the use of a mutual exclusion lock depends on the frequency that the data is read compared to being modified, the duration of the read and write operations, and the contention for the data - that is, the number of threads that will try to read or write the data at the same time.


* Many policy decisions that an implementation must make, which may affect the effectiveness of the read-write lock in a given application.
    * Determining whether to grant the read lock or the write lock, when both readers and writers are waiting, at the time that a writer releases the write lock. Writer preference is common, as writes are expected to be short and infrequent. Reader preference is less common as it can lead to lengthy delays for a write if the readers are frequent and long-lived as expected. Fair, or "in-order" implementations are also possible.
    * Determining whether readers that request the read lock while a reader is active and a writer is waiting, are granted the read lock. Preference to the reader can delay the writer indefinitely, while preference to the writer can reduce the potential for concurrency.
    * Determining whether the locks are reentrant: can a thread with the write lock reacquire it? Can it acquire a read lock while holding the write lock? Is the read lock itself reentrant?
    * Can the write lock be downgraded to a read lock without allowing an intervening writer? Can a read lock be upgraded to a write lock, in preference to other waiting readers or writers?


---

#### References:
* https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/ReadWriteLock.html