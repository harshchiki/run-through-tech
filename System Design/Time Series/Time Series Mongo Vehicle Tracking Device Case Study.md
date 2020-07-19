# Case Study

# Current set of tables
1. 2 days TPR
2. 60 days TPR
3. before 60 days TPR

# Problems
* Currently the data is copied over from table to table at periods
    * Time consumption
    * CPU consumption - hinders ongoing processing + traffic
    * Consider a conservative configuration of the machine in terms of CPU/Memory/Storage


# Principles/Facts/Cautions/Challenges around Time Series Data Handling
* Platforms that allow data to be scaled out and distributed across many nodes are much more suited to this type of use case than scale-up, monolithic tabular databases.
* Writes are relatively far higher - DB is always busy with write intensive operations. 
* Querying for historical data - leading to heavy reads - imposes read load on database.
* Depending on the application’s requirements, the data captured may have a shelf life and needs to be archived or deleted after a certain period of time.
* **SO BASICALLY, Working with Time Series Data = Storage + Simultaneous read and write demands + advanced querying capabilities + Archival etc.**


# Write workload
* What will the ingestion rate be? How many inserts and updates per second?
    * As the rate of inserts increases, your design may benefit from horizontal scaling via [MongoDB auto-sharding](https://docs.mongodb.com/manual/sharding/), allowing you to partition and scale your data across many nodes
* How many simultaneous client connections will there be?
    * While a single MongoDB node can handle many simultaneous connections from tens of thousands of IoT devices, you need to consider scaling those out with sharding to meet the expected client load.
* Do you need to store all raw data points or can data be pre-aggregated? If pre-aggregated, what summary level of granularity or interval is acceptable to store? Per minute? Every 15 minutes?
    * MongoDB can store all your raw data if your application requirements justify this. However, keep in mind that reducing the data size via pre-aggregation will yield lower dataset and index storage and an increase in query performance.
* What is the size of data stored in each event?
    * MongoDB has an individual document size limit of 16 MB. If your application requires storing larger data within a single document, such as binary files you may want to leverage MongoDB GridFS. Ideally when storing high volume time-series data it is a best practice to keep the document size small, around 1 disk block in size.



# Read workload
* How many read queries per second?
    * A higher read query load may benefit from additional [indexes](https://docs.mongodb.com/manual/indexes/) or horizontal scaling via MongoDB auto-sharding.
    * As with write volumes, reads can be scaled with auto-sharding. You can also distribute read load across secondary replicas in your replica set.
* Will clients be geographically dispersed or located in the same region?
    * You can reduce network read latency by deploying [read-only secondary replicas](https://docs.mongodb.com/manual/core/replica-set-architectures/#distribute-members-geographically) that are geographically closer to the consumers of the data.
* What are the common data access patterns you need to support? For example, will you retrieve data by a single value such as time, or do you need more complex queries where you look for data by a combination of attributes, such as event class, by region, by time?
    * Query performance is optimal when proper indexes are created. Knowing how data is queried and defining the proper indexes is critical to database performance. Also, being able to modify indexing strategies in real time, without disruption to the system, is an important attribute of a time-series platform.
* What analytical libraries or tools will your consumers use?
    * If your data consumers are using tools like Hadoop or Spark, MongoDB has a [MongoDB Spark Connector](https://docs.mongodb.com/spark-connector/current/) that integrates with these technologies. MongoDB also has drivers for Python, R, Matlab and other platforms used for analytics and data science.
* Does your organization use BI visualization tools to create reports or analyze the data?
    * MongoDB integrates with most of the major BI reporting tools including Tableau, QlikView, Microstrategy, TIBCO, and others via the MongoDB BI Connector. MongoDB also has a native BI reporting tool called MongoDB Charts, which provides the fastest way to visualize your data in MongoDB without needing any third-party products.

# Data retention and Archival
* What users and roles need to be defined, and what is the least privileged permission needed for each of these entities?
* What are the encryption requirements? Do you need to support both in-flight (network) and at-rest (storage) encryption of time series data?
* Do all activities against the data need to be captured in an audit log?
* Does the application need to conform with GDPR, HIPAA, PCI, or any other regulatory framework?
* The regulatory framework may require enabling encryption, auditing, and other security measures. MongoDB supports the security configurations necessary for these compliances, including encryption at rest and in flight, auditing, and granular role-based access control controls.


# Strategies to remove archive data in Mongo DB
* [TTL indexes](https://docs.mongodb.com/manual/core/index-ttl/)
* [Queryable Backups](https://youtu.be/NiKPxN5IECY)
* [Zoned Sharding](https://docs.mongodb.com/manual/core/zone-sharding/)
    * allowing you to create a tiered storage pattern

# Reduce Network read latency
* [Read-only seconday replicas](https://docs.mongodb.com/manual/core/replica-set-architectures/#distribute-members-geographically)

# Troubleshooting MongoDB query performance
* Check Your MongoDB Log
    ```
    use admin;
    db.runCommand({ logRotate : 1 });
    ```
    * By default, MongoDB records all queries which take longer than 100 milliseconds. Its location is defined in your configuration’s systemLog.path setting, and it’s normally /var/log/mongodb/mongod.log in Debian-based distributions such as Ubuntu.
    * The log file can be large, so you may want to clear it before profiling
* Analyse your queries
    * [Explain query/plan](https://docs.mongodb.com/manual/tutorial/analyze-query-plan/)
    * You can add explain('executionStats') to a query. For example:
        ```
        db.user.find(
        { country: 'AU', city: 'Melbourne' }
        ).explain('executionStats');
        ```

        OR append it to te collection
        ```
        db.user.explain('executionStats').find(
        { country: 'AU', city: 'Melbourne' }
        );
        ```
        * This returns a large JSON result, but there are two primary values to examine:
            * `executionStats.nReturned` — the number of documents returned, and
            * `executionStats.totalDocsExamined` — the number of documents scanned to find the result.
* Be wary of sorting
    * it works effectively, if you have an index defined. (single or compound)
* Create Two or More Connection Objects
    * When building an application, you can increase efficiency with a single persistent database connection object which is _reused_ for all queries and updates.
    * **MongoDB runs all commands in the order it receives them from each client connection.** While your application may make asynchronous calls to the database, every command is synchronously queued and must complete before the next can be processed. **If you have a complex query which takes ten seconds to run, no one else can interact your application at the same time on the same connection.**

    * Performance can be improved by defining more than one database connection object. Ofcourse without compromising with consistency of data. For example:
        * one to handle the **majority of fast queries**
        * one to handle **slower document inserts and updates**
        * one to handle **complex report generation.**
    * Each object is treated as a separate database client and will not delay the processing of others. The application should remain responsive.
* Rebuild your indexes
    * If you’re satisfied your structure is efficient yet queries are still running slowly, you could try rebuilding indexes on each collection. For example, rebuild the user collection indexes from the mongo command line:
        ```
        db.user.reIndex();
        ```
    