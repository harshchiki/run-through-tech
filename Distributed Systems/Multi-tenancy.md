# Multi-tenancy

----------------------------------------------------------------------------------------------------------------------

##### Multi-tenancy
1. MT is a value that depends on a continuous variable. Therefore, any statement about a system being “MT” can only be made in the context of the given requirements. It is not a property of the system itself.
2. I will also show that perfect multi-tenancy is indistinguishable from single-tenancy (ST).
MT is a value that depends on a continuous variable 

Imagine a step-function "ST-MT" (values are either 0 or 1) that determines if a given system is MT (1) or ST (0). That function will look like this:
ST-MT = function(system, business requirements)
Look at  the function’s arguments: the first one is obvious – the result will depend on the system itself.
The second one is more interesting: it is the cumulative set of business requirements. Typically, these requirements will include:

* **Resource sharing**: systems typically declared as being MT share some resources. That can be network, storage, compute, etc. The business requirements specify which of these resources are acceptable to be shared and which are not.
* **Tenant isolation**: the business requirements specify the required level of tenant isolation, meaning which level of “noisy neighbour” problem is acceptable. That noisy neighbour could affect the resource sharing, but also has a heavy influence on the acceptable security requirements.
* **Extensibility requirements**: how can tenants extend or tweak the system. Consider setting variables or deploying custom code into the system.
The first observation to make is that these input variables are continuous. That means:
Any given system can either be MT or ST depending on the values of the cumulative requirements.
Let me work through some example values for the requirements that could affect the value of the ST-MT-function:
 

##### Resource sharing

* Network: multiple tenants might share parts of the network, e.g. Local network interfaces, internet connection, routers, etc. One given tenant might saturate the network and affect other tenants, maybe even make the system inaccessible for these tenants 
* Storage: the system might allow tenants to submit queries to the storage subsystem. Those queries could be expensive/long-running and affect the storage’s response times to other tenants. 
* Compute: same as above for shared compute resources.
 

##### Tenant isolation

It is to be specified by business requirements which isolation levels between tenants are required. The considerations above regarding “resource sharing” fall into this bucket, but there is more:
It is a business consideration if the physical storage system can be shared between tenants. For example, some customers require storage to be physically separated from their competitors data/content.
 

##### Extensibility

Business requirements might require to allow tenants to independently adjust system settings (properties). For example, it might be required that the URL space can be created for each tenant independently, so that any tenant could independently claim the URL "/abc”. Similarly, it could also be required that any tenant can independently change setting like “max time to execute a function”, “max number of users in a group”, etc.
More interestingly, the business might require that the tenants are allowed to upload executable code into the system. This input variable usually has a large impact on the value of the ST-MT-function.
In the light of this let’s look at some given systems and ponder whether they are MT or ST:
 

##### DBs 

Consider a plain old RDBMS like MySQL or Oracle. Do you think it is MT or ST?
One can create DB users with respective read/write rights for each tenant such that that the users cannot “see” each other. However, what if one tenant saturates the JDBC/ODBC connections? Same question for compute resources required for queries.
As a concrete example: consider the (now deprecated) Parse platform – which was a MBaaS. Customers could simply sign up and get a MBaaS. However, they would share the same underlying MongoDB. Guess what: it is extremely easy to write a Mongo query that eats up all system resources – which would slow down or break the system for all other tenants.
It is clear: the very same DB technology can be considered either ST or MT – it entirely depends on your requirements.
 

##### A web gateway

Consider a reverse proxy (or even a cluster of reverse proxies) that serve as an entry point to many backend services. Let's say for the sake of the argument that it is Nginx-based. Most would probably argue that such a setup is MT (because adding new tenants does not require changing the system). Well, let me come up with a new requirement: a malicious tenant shall not be able to break the system (i.e. make the system unavailable for other tenants). Well, obviously it is always a possibility that there is a bug in Nginx which one tenant might exploit or hit accidentally (see old CVE).
Is this an unacceptable risk and hence makes that gateway unsuitable for MT purposes? Would it make the system ST? Again: this is just a business decision.
 

##### A consumer-facing storage system

Consider a content repository that is largely thought of as being “MT” (e.g. Dropbox). Consider a new enterprise customer who demands that the physical storage of his files is to be physically separated from any other tenants. If you bring in this requirement and if the backend of the system does not physically separate files then these new tenants must be deployed onto their own backend storage servers. Does this make the system “ST”?
At this point I hope to have made the point clear that a system by itself cannot be called MT or ST without specifying further business constraints.
Let me move to the second point:
Perfect multi-tenancy is indistinguishable from single-tenancy


----------------------------------------------------------------------------------------------------------------------

Reading the above you might be tempted to think that MT is a continuum with ST at one end and MT at the other end. This is somewhat true – but:
The continuum is a circle and MT and ST fall onto the same point.
How is that possible?
Imagine that you want to set out to design a system that satisfies ALL POSSIBLE business requirements towards multi-tenancy. You would separate storage, network, compute etc etc. The resulting system would be indistinguishable from a number a ST systems that sit next to each other.
In conclusion: the systems that are commonly called multi-tenant could be called “single-tenant systems in which the business requirements allowed to share certain resources”. Or to put it the other way around “what is commonly know as ST systems are simply perfectly designed MT systems that satisfy all possible requirements on multi-tenancy”.
Funny, no?


----------------------------------------------------------------------------------------------------------------------
### References:
* http://michaelmarth.blogspot.com/2016/12/what-is-multi-tenancy-closer-look.html
