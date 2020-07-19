# Java Streams
### Introduction
* Lambdas make it easy to express **behavior as data**
* Enables the concise and declarative expression of possibly-parallel bulk operations on various data sources.


### Querying with Streams
* One of the most common uses of streams is to represent queries over data in collections.
* Example
    ```
    int totalSalesFromNY
            = txns.stream()
                .filter(t ‑> t.getSeller().getAddr().getState().equals("NY"))
                .mapToInt(t ‑> t.getAmount())
                .sum();
    ```
* How streams enable/help readability?
  
    **From**:
    ```
    Set<Seller> sellers = new HashSet<>();
    for (Txn t : txns) {
        if (t.getBuyer().getAge() >= 65)
            sellers.add(t.getSeller());
    }
    List<Seller> sorted = new ArrayList<>(sellers);
    Collections.sort(sorted, new Comparator<Seller>() {
        public int compare(Seller a, Seller b) {
            return a.getName().compareTo(b.getName());
        }
    });
    for (Seller s : sorted)
        System.out.println(s.getName());
    ```

    **To**:
    ```
    txns.stream()
        .filter(t ‑> t.getBuyer().getAge() >= 65)
        .map(Txn::getSeller)
        .distinct()
        .sorted(comparing(Seller::getName))
        .map(Seller::getName)
        .forEach(System.out::println);
    ```


### Anatomy of a Stream pipeline
* Common structure of all stream operations. They have:
    * Stream source
    * 0 or more intermediate operations
    * Terminal operation
* Since most of the Java programs store data in collections, many stream computations use collections as their source.

<br /> <br />


## Stream sources in JDK

* Create a stream from the elements of a collection.

        Collection.stream(): 

* Create a stream from the arguments passed to the factory method.

      Stream.of(T...): 

* Create a stream from the elements of an array.

      Stream.of(T[])`

* Create an empty stream.

      `Stream.empty()	

* Create an infinite stream consisting of the sequence first, f(first), f(f(first)), ...

      Stream.iterate(T first, BinaryOperator(less-thanT> f)	



* Similar to Stream.iterate(T first, BinaryOperator(less-thanT> f), except the stream terminates on the first elements for which the test predicate returns false.
(**Java 9 only**) 

      Stream.iterate(T first, Predicate(less-thanT> test, BinaryOperator(less-thanT> f)	


* Create an infinite stream from a generator function.

      Stream.generate(Supplier(less-thanT> f)


* Create an IntStream consisting of the elements from lower to upper, exclusive.

      IntStream.range(lower, upper)


* Create an IntStream consisting of the elements from lower to upper, inclusive.

      IntStream.rangeClosed(lower, upper)


* Create a stream consisting of the lines from a BufferedReader.

        BufferedReader.lines()

* Create an IntStream consisting of the indexes of the set bits in a BitSet.

        BitSet.stream()


* Create an IntStream corresponding to the chars in a String.

      CharSequence.chars()


<br /> <br />

## Intermediate Stream operations

* The elements of the stream matching the predicate
  
        filter(Predicate(less-thanT>)	


* The result of applying the provided function to the elements of the stream

        map(Function(less-thanT, U>)	


* The elements of the streams resulting from applying the provided stream-bearing function to the elements of the stream

        flatMap(Function(less-thanT, Stream(less-thanU>>	


* The elements of the stream, with duplicates removed

        distinct()	


* The elements of the stream, sorted in natural order

        sorted()	


* The elements of the stream, sorted by the provided comparator

        Sorted(Comparator(less-thanT>)	


* The elements of the stream, truncated to the provided length

        limit(long)	


* The elements of the stream, discarding the first N elements

        skip(long)	



* The elements of the stream, truncated at the first element for which the provided predicate is not true
(**Java 9 only**) 

        takeWhile(Predicate(less-thanT>)	


* The elements of the stream, discarding the initial segment of elements for which the provided predicate is true. (**Java 9 only**) 

        dropWhile(Predicate(less-thanT>)	


<br />


### Lazy operations
* Intermediate operations are always lazy: 
    * Invoking an intermediate operation merely sets up the next stage in the stream pipeline but doesn’t initiate any work. 
    * Intermediate operations are further divided into stateless and stateful operations. 
        * **Stateless operations** (such as `filter()` or `map()`) can operate on each element independently, whereas 
        * **Stateful operations** (such as `sorted()` or `distinct()`) can incorporate state from previously seen elements that affects the processing of other elements.


<br /> <br />


### Terminal Stream Operations
* The **processing** of the data set **begins** when a **terminal operation is executed**, such as a 
    * **reduction** (`sum()` or `max()`), 
    * **application** (`forEach()`), or 
    * **search** (`findFirst()`) operation.



### Streams vs collections
* A **collection** is a **data structure**; its main concern is the **organization** of **data** in memory, and a collection **persists** **over a period of time**.
* A collection might often be used as the source or target for a stream pipeline, but a **stream’s** focus is on **computation**, **not data**.
* **Streams** provide **no storage**
* In terms of how **operations are executed**
    * Operations on collections are eager and mutative; `remove()` on `List` modifies its state.
    * For streams, only the terminal operation is eager, rest are lazy
    * **Stream** operations represent a f**unctional transformation on their input (also a stream)**, rather than a mutative operation on a data set (filtering a stream produces a new stream whose elements are a subset of the input stream but doesn’t remove any elements from the source).
    * Each stage of a stream pipeline produces its elements lazily, computing elements only as needed, and feeds them directly to the next stage. 



### Parallelism
* Example

        ```
        int totalSalesFromNY
                = txns.parallelStream()
                    .filter(t ‑> t.getSeller().getAddr().getState().equals("NY"))
                    .mapToInt(t ‑> t.getAmount())
                    .sum();
        ```


Next up: TODO: https://developer.ibm.com/articles/j-java-streams-2-brian-goetz/

----------------------------------------------------------------------------------------------------------------------
### References:
* https://developer.ibm.com/articles/j-java-streams-1-brian-goetz/
* https://developer.ibm.com/articles/j-java-streams-2-brian-goetz/