# Cloud Native architecture
----------------------------------------------------------------------------------------------------------------------
From [0]

##### Cloud Native Architecture:
* Adapting to many new possibilities
* Adapting to different set of architectural constraints - offered by cloud as opposed to on-premise infrastructure.

**Consider**:
* **Functional requirements**
    * What it should do?
* **Non functional requirements**
    * How it should perform?
* **Constraints**
    * What is out of scope to change

> A well-architected cloud native system, on the other hand, should be largely self-healing, cost efficient, and easily updated and maintained through Continuous Integration/Continuous Delivery (CI/CD).

Almost all the principles of good architecture design apply to cloud architecture, however, some of the fundamental assumptions about how that fabric performs change when you’re in the cloud. 
* e.g. provisioning a replacement server can take weeks in traditional environments, whereas in the cloud, it takes seconds—your application architecture needs to take that into account.

##### Principles:
1. Design for automation
2. Be smart with state
3. Favour managed services
4. Practise defence in depth
5. Always be architecting



----------------------------------------------------------------------------------------------------------------------
### References:
* [0] 5 Principles of Clouse Native Architecture (Google)
  * https://cloud.google.com/blog/products/application-development/5-principles-for-cloud-native-architecture-what-it-is-and-how-to-master-it