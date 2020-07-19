# Fallacies of Distributed Systems

----------------------------------------------------------------------------------------------------------------------
* Network is reliable (It is not)
* Latency is zero (It varies, and many times, overshoots the KPI)
* Bandwidth is infinite (Are you kidding?)
* Network is secure (You’re going nuts!)
* Topology doesn’t change 
* There is one administrator
* Transport cost is zero
* Network is homogeneous


##### Effects of fallacies
* Software applications are written with little error-handling on networking errors. During a network outage, such applications may stall or infinitely wait for an answer packet, permanently consuming memory or other resources. When the failed network becomes available, those applications may also fail to retry any stalled operations or require a (manual) restart.
* Ignorance of network latency, and of the packet loss (https://en.wikipedia.org/wiki/Packet_loss) it can cause, induces application- and transport-layer developers to allow unbounded traffic, greatly increasing dropped packets and wasting bandwidth.
* Ignorance of bandwidth limits on the part of traffic senders can result in bottlenecks.
* Complacency regarding network security results in being blindsided by malicious users and programs that continually adapt to security measures.[2]
* Changes in network topology (https://en.wikipedia.org/wiki/Network_topology) can have effects on both bandwidth and latency issues, and therefore can have similar problems.
* Multiple administrators, as with subnets (https://en.wikipedia.org/wiki/Subnetwork) for rival companies, may institute conflicting policies of which senders of network traffic must be aware in order to complete their desired paths.
* The "hidden" costs of building and maintaining a network or subnet are non-negligible and must consequently be noted in budgets to avoid vast shortfalls.
* If a system assumes a homogeneous network, then it can lead to the same problems that result from the first three fallacies.


So, if someone were to consider the cost of going from a distributed system to a monolith, above will hold true the other way round. A distributed system installed as a monolith will under perform (because of the over optimisations in the situation/deployment context)



----------------------------------------------------------------------------------------------------------------------
### References:
* https://en.wikipedia.org/wiki/Fallacies_of_distributed_computing