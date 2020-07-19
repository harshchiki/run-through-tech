### Java - Concurrent Skip List Map
---------------------------------------

####Class ConcurrentSkipListMap<K,V>

    * java.lang.Object
        * java.util.AbstractMap<K,V>
            * java.util.concurrent.ConcurrentSkipListMap<K,V>
    Type Parameters:
    * K - the type of keys maintained by this map
    * V - the type of mapped values
    * All Implemented Interfaces:
        Serializable, Cloneable, ConcurrentMap<K,V>, ConcurrentNavigableMap<K,V>, Map<K,V>, NavigableMap<K,V>, SortedMap<K,V>
    
    public class ConcurrentSkipListMap<K,V>
    extends AbstractMap<K,V>
    implements ConcurrentNavigableMap<K,V>, Cloneable, Serializable




* Scalable concurrent ConcurrentNavigableMap implementation.
* **The map is sorted according to the natural ordering of its keys, or by a Comparator provided at map creation time, depending on which constructor is used.**

---

#### SkipList
* In computer science, it is a DS with

    >**Search complexity: O(log n)**
    >**Insertion complexity: O(log n)**


* Above is within an ordered sequence of n elements.


* **Thus it can get the best of array (for searching) while maintaining a linked list like structure that allows insertion which is not possible in array (requires swaps).**


* **Adv**: Perform very well on rapid insertions because there are no rotations or reallocations.


* **Adv**: Simpler to implement that both self balancing binary search tree and hash tables.


* **Adv**: You can retrieve the next element in constant time (compare to logarithmic time for ignorer traversal for BSTs and linear time in hash tables)


* **Adv**: the algorithms can easily be modified to a more specialised structure (like segment or range “trees”, indexable skip lists, or keyed priority queues)


* **Adv**: Making it lockless is simple.


* **Adv**: It does well in persistent (slow) storage (often better than AVL)


---

#### A naive implementation of SkipList:
*(to be completed)*


---

###References:
- ConcurrentSkipListMap - https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentSkipListMap.html

- SkipList: https://en.wikipedia.org/wiki/Skip_list

- SkipList: http://ticki.github.io/blog/skip-lists-done-right/