# Threading and Synchronization Performance

# Turn around time
* amount of time taken to complete a process or fulfill a request.

# Lead time
* the time gap between the order palced by the customer and the time when the customer gets the final delivery
* How is it different from Turn around time? -> 

# Throughput

# Java thread states
* A thread state. A thread can be in one of the following states:
    * ## NEW
        * A thread that has **not yet started** is in this state.
    * ## RUNNABLE
        * A thread **executing** in the Java virtual machine is in this state.
    * ## BLOCKED
        * A thread that is blocked **waiting for a monitor lock** is in this state.
        * for e.g. 2 members A and B of a family owning 1 car.  A needs to go to office, but B is out to buy some grocery. A is blocked now. Essentially B has taken over the lock on the car, which itself can be accesses in a synchronized block, because it is shared. Given car is resource, which can be accessed mutually exclusively, it has a monitor/lock, on which the contender wait on. Once B returns, he releases the lock, and A acquires.
    * ## WAITING
        * A thread that is **waiting** indefinitely for another thread to perform a particular action is in this state.
        * For e.g. (adding to th example of Blocked state) when A was heading office, he jumped a signal, and was caught by the traffic police. Now A gets into a WAITING state, releasing the lock of the car,
            * Methods
                * `Object.wait() with no timeout`
                * `Thread.join() with no timeout`
                * 
    * ## TIMED_WAITING
        * A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state.
    * ## TERMINATED
        * A thread that has exited is in this state.


# Experiment
* Empty main method with thread sleep of 25 seconds
    * RUNNABLE 10 - { Reference Handdler, Signal Dispatcher, C2 CompilerThread0, C1 CompilerThread0, Sweeper thread, Service Thread, C2 CompilerThread1, C2 CompilerThread2, C2 CompilerThread3, AttachListener }
    * WAITING 2 - { main thread (due to Thread.sleep), Finalizer }
    * TIMED_WAITING 2 - { Common-Cleaner, main }
* Fixed thread pool created with 10 threads and then thread sleep for 10 seconds to capture thread dump
    * RUNNABLE 7 - { Reference Handdler, Signal Dispatcher, C2 CompilerThread0, C1 CompilerThread0, Sweeper thread, Service Thread, AttachListener }
    * WAITING 2 - { main, Finalizer }
    * TIMED_WAITING 2 - { Common-Cleaner, main thread }
* submitting to executor after sleep for n times, with each task itself having some sleep - randomly captured thread dumps
    * TIMED_WAITING 12 - { Common-Cleaner, main thread, 10 threads in the executor }
    * RUNNABLE 7 - { Reference Handdler, Signal Dispatcher, C2 CompilerThread0, C1 CompilerThread0, Sweeper thread, Service Thread, AttachListener }
    * WAITING 2 - { main, Finalizer }

# References
* Book - Java Performance
* Thread states - https://www.youtube.com/watch?v=fzYLtYaJ_D0