# Java - Analyzing Thread Dumps


### Contention
  * Thread contention is a status in which one thread is waiting for a lock, held by another thread, to be lifted. Different threads frequently access shared resources on a web application. 




### Thread Status
  * **NEW**
    * thread is created but not processed yet
  * **RUNNABLE**
    * The thread is occupying the CPU and processing a task. (It may be in WAITING status due to the OS's resource distribution.)
  * **BLOCKED**
    * waiting to acquire a lock, likely some other thread has acquired, and not released yet (has the CPU)
    * The thread is waiting for a different thread to release its lock in order to get the monitor lock.
  * **WAITING**
    * The thread is waiting by using a wait, join or park method.
  * **TIMED_WAITING**
    * The thread is waiting by using a sleep, wait, join or park method. (The difference from WAITING is that the maximum waiting time is specified by the method parameter, and WAITING can be relieved by time as well as external changes.) 
  * **TERMINATED**
    * A thread that has exited.




### Thread Types
1. **Daemon**
   1. stop working when there are no other non-daemon threads.
   2. Even if you do not create any threads, the Java application will create several threads by default. Most of them are daemon threads, mainly for processing tasks such as garbage collection or JMX.
2. **Non-daemon**
   1.  thread running the `static void main(String[] args)` method is created as a non-daemon thread, and when this thread stops working, all other daemon threads will stop as well. (The thread running this main method is called the VM thread in HotSpot VM.)



### Getting a thread dump
1. Using `jstack`
   1. Use PID via jps to check the PID of the currently running Java application process.   

        ```
        [user@linux ~]$ jps -v
        25780 RemoteTestRunner -Dfile.encoding=UTF-8
        25590 sub.rmi.registry.RegistryImpl 2999 -Dapplication.home=/home1/user/java/jdk.1.6.0_24 -Xms8m
        26300 sun.tools.jps.Jps -mlvV -Dapplication.home=/home1/user/java/jdk.1.6.0_24 -Xms8m
        ```

   2. Use the extracted PID as the parameter of jstack to obtain a thread dump.

        ```
        [user@linux ~]$ jstack -f 5824
        ```

2. Using JVisualVM
   1. Get Java process id

        ```
        > ps -ef | grep java
        ```

   2. Use the extracted pid as the parameter of kill –SIGQUIT(3) to obtain a thread dump.

        ```
        > kill -3 <pid>
        ```



## Analyzing Thread Dump
* Thread Dump
  * A thread dump is a snapshot of everything that an application is doing right at the instance it's taken. If an application log is a diary of a vacation, a thread dump is a photo.
  * A collection of stack traces, one for each thread that's running in the instance.
* Consider the following **thread dump snapshot**


        "http-bio-80-exec-77" daemon prio=6 tid=0x0000000026f29000 nid=0xbd0 runnable [0x0000000020c7f000]
            java.lang.Thread.State: RUNNABLE
                at java.net.SocketInputStream.socketRead0(Native Method)
                at java.net.SocketInputStream.read(Unknown Source)
                at java.net.SocketInputStream.read(Unknown Source)
                at org.apache.coyote.http11.InternalInputBuffer.fill(InternalInputBuffer.java:516)
                at org.apache.coyote.http11.InternalInputBuffer.fill(InternalInputBuffer.java:501)
                at org.apache.coyote.http11.Http11Processor.setRequestLineReadTimeout(Http11Processor.java:167)
                at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:946)
                at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:607)
                at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:315)
                - locked <0x00000007b16e3e88> (a org.apache.tomcat.util.net.SocketWrapper)
                at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
                at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
                at java.lang.Thread.run(Unknown Source)


    * Name of the thread: "http-bio-80-exec-77"
      * This tells us quite a few things:
        * The **port** the instance is running on:http-bio-80-exec-77 tells us we're running on port 80
        *  What **sort of connector** is being used: http-bio-80-exec-77 tells us we're using the Java BIO connector, the default for Tomcat 7
        * The number of the thread: http-bio-80-exec-77 tells us this http thread #77, w*hich isn't very useful information by itself, but very useful when we look at the thread across multiple dumps to see if what it's doing has changed*
    * Thread State: RUNNABLE
    * Priority:
    * Thread ID:
    * Thread Status:
    * Thread callstack:
* Thread dumps help the most, when taken at the peak of (bad/undesired) performance
  * Perhaps, 6 dumps, every 10 seconds, can guide you on the frequency and duration.
* Look for the long running threads
  * This you can determine from consecutive dumps
* ### Red Herrings
  * Generally the focus is on RUNNABLE threads. However, a thread being in the RUNNABLE state does not necessarily indicate a problem. For e.g. there is always one GC thread per for, and these will always be RUNNABLE, whether they're currently performing a collection or not. That tey are RUNNABLE doesn't mean that GC is a problem, however, even though it looks that way at first glance, as they're likly not collecting.
* ### Blocked Threads
  * Outside of RUNNABLE threads, the other major problem, thread dumps reveal are the BLOCKED threads.
* ### CPU Usage
  * To know which threads are chewing up the CPU of the machine that application is running on

        > top -n1 -b -H

        // TODO: CHECK if the command is valid. Did not RUN.

* ### Thread Dump Patterns by Type
  * When Unable to obtain a lock (Blocked)
    * performance slows down -> a thread is occupying the lock and prevents other threads from obtaining it.
    * 


### Commands

| Purpose                                                                 | Command                | Description/Remarks            |
| ----------------------------------------------------------------------- | ---------------------- | ------------------------------ |
| *Get List of all jvm processes*                                         | **jcmd**               |                                |
| *Show actions/commands you can perform for performance analysis/tuning* | **jcmd <pid> help**    |                                |
| *Executing a possible command from previous step*                       | **jcmd <pid> command** | e.g. > sjcmd 3036 GC.heap_dump |





----------------------------------------------------------------------------------------------

## References