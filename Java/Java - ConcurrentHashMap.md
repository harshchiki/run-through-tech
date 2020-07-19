# Concurrent Hash Map
<https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html>

## Implemented interfaces
* [Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)
* [ConcurrentMap](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentMap.html)<K,V>, 
* [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)<K,V>

## Notes on ConcurrentHashMap
* A hash table supporting full concurrency of retrievals and high expected concurrency for updates.
* obeys the same functional specification as [Hashtable](https://docs.oracle.com/javase/8/docs/api/java/util/Hashtable.html)
* **While all operations are thread safe, reads don't entail locking**
    * Retrieval operations (including get) generally do not block, so may overlap with update operations (including put and remove).
* **There is no support of locking the entire table in a way that prevents all access**
* Iterators, Spliterators and Enumerations return elements reflecting the state of the hash table at some point at or since the creation of the iterator/enumeration. 
    * **They do not throw ConcurrentModificationException.**
* Iterators are designed to be used by only one thread at a time. 
    * The results of aggregate status methods including `size`, `isEmpty`, and `containsValue` **are typically useful only when a map is not undergoing concurrent updates in other threads**. 
    * **Otherwise the results of these methods reflect transient states that may be adequate for monitoring or estimation purposes, but not for program control.**
* 