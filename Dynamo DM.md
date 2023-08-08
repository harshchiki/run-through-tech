* Reliability and scalability of a system is dependent on how its application state is managed. 
  * For example, customers should be able to view and add items to their shopping cart even if disks are failing, network routes are flapping, or data centers are being destroyed by tornados. 
    * Therefore, the service responsible for managing shopping carts requires that it can always write to and read from its data store, and that its data needs to be available across multiple data centers.  



# References
Peper: Dynamo: Amazon’s Highly Available Key-value Store 