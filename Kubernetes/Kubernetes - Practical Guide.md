# Kubernetes

## History and challenges of Infra mgmt
* Manual provisioning and installations were a nightmare because even after putting a lot of effort into documentation, given enough time, the state of the servers would always diverge from the documentation. Sysadmins were the key people without whom no one can handle these servers.
* ### Configuration Management Tools


# Cluster  scheduler
* functions
  * ensure resources are used efficiently and within constraints
  * ensure services are (almost) always running
  * provide fault tolerance and high availability
  * ensure specified number of replicas are deployed.
  * ensure the desired state requirement of a service or a node is (almost) always fulfilled.
      * instead of using imperative methods to achieve our goals, with scheduled, we can be declarative.
  * we can tell a scheduled what the desired state is, and it will do its best to ensure that our desire if (almost) alwyas fullfilled.

# Container Schedulers
* Use containers as the deployment units
* deploy services packaged as container images
* collocate them depending on desired memory and CPU specifications
* ensure that the desired number of replicas are (almost) always running.

# What is Kubernetes?
## Why we need Kubernetes OR why running containers directly is a bad option for most use cases?
* Containers are low level entities that require a framework on top.
* Something needs to provide them, all the additional features we expected from the services deployed to clusters.
* **Containers, by themselves, do not provide fault tolerance**
## Why Kubernetes?
* We can use it to deploy our services, to roll out new releases without downtime, and to scale (or de-scale) those services.
* It is portable.
* It can run on a public or private cloud.
* It can run on-premise or in a hybrid environment.
* We can move a Kubernetes cluster from one hosting vendor to another without changing (almost) any of the deployment and management processes.
* Kubernetes can be easily extended to serve nearly any needs. We can choose which modules we’ll use, and we can develop additional features ourselves and plug them in.
* Kubernetes will decide where to run something and how to maintain the state we specify.
* Kubernetes can place replicas of a service on the most appropriate server, restart them when needed, replicate them, and scale them.
* Self-healing is a feature included in its design from the start. On the other hand, self-adaptation is coming soon as well.
* Zero-downtime deployments, fault tolerance, high availability, scaling, scheduling, and self-healing add significant value in Kubernetes.
* We can use it to mount volumes for stateful applications.
* It allows us to store confidential information as secrets.
* We can use it to validate the health of our services.
* It can load balance requests and monitor resources.
* It provides service discovery and easy access to logs.
* And so on and so forth.

# kubectl
* command line tool used to manage a cluster and applications running inside it.