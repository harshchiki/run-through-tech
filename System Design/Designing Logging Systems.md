# Desiging Logging Systems

----------------------------------------------------------------------------------------------------------------------

##### Designing Logging Systems

The logging model used by Dynamo provides a flexible mechanism for setting up complex application logging rules. With a combination of filters and sinks, you can design a logging configuration that handles all requirements.
The key to designing logging systems is to model your logging rules in terms of the logging filters and sinks provided with Dynamo (or with new filters and sinks that you write yourself).
For example, if you want to monitor a particular component so errors are sent as email, but all messages, including errors, are sent to a single file, you need the following:
* LogListenerQueue, to ensure the component is not hampered by the logging processes
* DispatchLogger that:
    * receives events from the LogListenerQueue
    * defines only the logDestinations property
    * distributes all events to two listeners
* Another DispatchLogger that feeds from the first DispatchLogger but only recognizes ErrorLogEvents
* EmailLogger to receive events from the second DispatchLogger and to send those events as email
* RotatingFileLogger to receive all events from the first DispatchLogger and write those events to a file
Finally, the log source component must specify the LogListenerQueue as one of its logListeners.
Here is an example of what a logging system might look like:

![](2019-09-28-21-37-38.png)


----------------------------------------------------------------------------------------------------------------------

### References:
