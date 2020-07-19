# Challenges in cloud development
* Availability
* Data Management
* Design and Impl
* Messaging
* Management and Monitoring
* Performance and Scalability
* Resiliency
* Security


# Ambassador pattern
* ref: <https://docs.microsoft.com/en-us/azure/architecture/patterns/ambassador>
* [x] Design and Implementation
* [x] Management and Monitoring

# Anti Corruption layer
* ref: <https://docs.microsoft.com/en-us/azure/architecture/patterns/anti-corruption-layer>
* [x] Design and Implementation
* [x] Management and Monitoring

# Event Sourcing
* ref: <https://docs.microsoft.com/en-us/azure/architecture/patterns/event-sourcing>
* [x] Data Management
* [x] Performance and Scalability
* Store storing an "append-only" log of actions taken on data.
  * Can be used to materialize the domain objects.
* Benefit: audit trail, history
* Issues and considerations:
  * The system will only be eventually consistent when creating materialized views or generating projections of data by replaying events.
  * 

# References
* <https://docs.microsoft.com/en-us/azure/architecture/patterns/>