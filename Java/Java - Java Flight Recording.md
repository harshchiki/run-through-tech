# Java Flight Recording

### Can't open Flight Recording - TOO BIG!!
Incase If your JFR file is quite big you can start JMC from the command line by specifying a custom heap size.  i.e. 
> $JAVA_HOME/bin/jmc -J-Xms8G -J-Xmx8G


### Sample count
It is the number of samples the flight records could collect. Lesser the number, less reliable the result, and true the other way round.




<p>
----------------------------------------------------------------------------------------------------------------------


## References:
* Diagnozing high CPU usage
  * https://www.ateam-oracle.com/diagnozing-high-cpu-problems-with-jrockit-mission-control
