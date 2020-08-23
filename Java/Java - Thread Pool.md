# Java  - Thread Pool
## Multi-threaded programming
* In a way, disadvantages of multithreaded programming
  * increased complexity
  * concurrency issues
  * unexpected resutls
  * overhead of thread creation (and disposal)
    * Creating and starting a thread can be an expensive process -> leads to siginificant performance cost.

## Creation and Starting of Threads by JVM - Behind the scenes
* It allocates memory for a thread stack that holds a frame for every thread method incocation.
* Each frame consists of a local variable array, return value, operand stack, and constant pool.
* Some JVMs that support native methods also allocate a native stack.
* Each thread gets a program counter that tells it what the current instruction executed by the process is.
* The system creates a native thread corresponding to the Java thread.
* Descriptors relative to the thread are added to the JVM internal data structures.
* The threads share the heap and method area.

Above is largely what happens, and does vary in implementation with JVM and operating system.


## Java Threadpools
* The _java.util.concurrent_ package cntains:
    * _Executor_: simple interface for executing tasks
    * _ExecutorService_: more complex interface which contains additional methods for managing the tasks and the executor itself.
    * _ScheduledExecutorService_ - extends _ExectutorService_ with methods for scheduling the execution of a task.
* A Java threadpool is composed of:
    * The pool of worker threads, responsible for managing the threads.
    * A thread factory that is responsible for creating new threads.
    * A queue of tasks waiting to be executed.

## Executors.newSingleThreadExecutor
* 1 thread
* unbounded queue
* (ofcourse) executes one task at a time


## Executors.newFixedThreadPool
* a fixed numver of threads
* **share** an unbounded queue; 
    * if all threads are active when a new task is submitted, they will wait in the queue, until a thread becomes available.

## Executors.newCachedThreadPool
* creates new threads as they are needed.

## Executors.newWorkStealingThreadPool
* a thread pool based on "work-stealing" algorithm.

## ExecutorService
* _Note_: **It is not automativally destroyed when there are no tasks waiting to be executed, so to shut it down explicitly, you can use the _shutdown()_ or _shutdownNow()_ APIs**
* 2 implementations
    * ThreadPoolExecutor
    * ForkJoinPool


## ThreadPoolExecutor
* 




# References
* <https://dzone.com/articles/getting-the-most-out-of-the-java-thread-pool>