# A Memory-Efficient Doubly Linked List

----------------------------------------------------------------------------------------------------------------------

##### Node Definition
We define a node of pointer distance implementation like this:

    typedef int T;
    typedef struct listNode{
        T elm;
        struct listNode * ptrdiff;
    };

The `ptrdiff` pointer field holds the difference between the pointer to the next node and the pointer to the previous node. Pointer difference is captured by using exclusive OR. Any instance of such a list has a StartNode and an EndNode. StartNode points to the head of the list, and EndNode points to the tail of the list. By definition, the previous node of the StartNode is a NULL node; the next node of the EndNode also is a NULL node. For a singleton list, both the previous node and next node are NULL nodes, so the ptrdiff field holds the NULL pointer. In a two-node list, the previous node to the StartNode is NULL and the next node is the EndNode. The ptrdiff of the StartNode is the exclusive OR of EndNode and NULL node: EndNode. And, the ptrdiff of the EndNode is StartNode.


##### Traversal
The insertion and deletion of a specific node depends on traversal. We need only one simple routine to traverse back and forth. If we provide the StartNode as an argument and because the previous node is NULL, our direction of traversal implicitly is defined to be left to right. On the other hand, if we provide the EndNode as an argument, the implicitly defined direction of traversal is right to left. The present implementation does not support traversal from the middle of the list, but it should be an easy enhancement. The NextNode is defined as follows:

    typedef listNode * plistNode;
    plistNode NextNode( plistNode pNode,
                        plistNode pPrevNode){
        return ((plistNode)
        ((int) pNode->ptrdiff ^ ( int)pPrevNode) );
    }

Given an element, we keep the pointer difference of the element by exclusive ORing of the next node and previous node. Therefore, if we perform another exclusive OR with the previous node, we get the pointer to the next node.

##### Insertion
Given a new node and the element of an existing node, we would like to insert the new node after the first node in the direction of traversal that has the given element (Listing 1). Inserting a node in an existing doubly linked list requires pointer fixing of three nodes: the current node, the next node of the current node and the new node. When we provide the element of the last node as an argument, this insertion degenerates into insertion at the end of the list. We build the list this way to obtain our timing statistics. If the InsertAfter() routine does not find the given element, it would not insert the new element.

* Listing 1. Function to Insert a New Node

        void insertAfter(plistNode pNew, T theElm) {
        plistNode pPrev, pCurrent, pNext;
        pPrev = NULL;
        pCurrent = pStart;

        while (pCurrent) {
            pNext = NextNode(pCurrent, pPrev);
            if (pCurrent->elm == theElm) {
                /* traversal is done */
                if (pNext) {
                    /* fix the existing next node */
                    pNext->ptrdiff =
                        (plistNode) ((int) pNext->ptrdiff
                                ^ (int) pCurrent
                                ^ (int) pNew);

                    /* fix the current node */
                    pCurrent->ptrdiff =
                    (plistNode) ((int) pNew ^ (int) pNext
                                ^ (int) pCurrent->ptrdiff);

                    /* fix the new node */
                    pNew->ptrdiff =
                        (plistNode) ((int) pCurrent
                                ^ (int) pNext);
                break;
                }
            pPrev = pCurrent;
            pCurrent = pNext;
            }
        }
    }

First, we traverse the list up to the node containing the given element by using the NextNode() routine. If we find it, we then place the node after this found node. Because the next node has pointer difference, we dissolve it by exclusive ORing with the found node. Next, we do exclusive ORing with the new node, as the new node would be its previous node. Fixing the current node by following the same logic, we first dissolve the pointer difference by exclusive ORing with the next current node. We then do another exclusive ORing with the new node, which provides us with the correct pointer difference. Finally, since the new node would sit between the found current node and the next node, we get the pointer difference of it by exclusively ORing them.


##### Deletion
The current delete implementation erases the whole list. For this article, our objective is to show the dynamic memory usage and execution times for the implemented primitives. It should not be difficult to come up with a canonical set of primitive operations for all the known operations of a doubly linked list.
Because our traversal depends on having pointers to two nodes, we cannot delete the current node as soon as we find the next node. Instead, we always delete the previous node once the next node is found. Moreover, if the current node is the end, when we free the current node, we are done. A node is considered to be an end node if the NextNode() function applied to it returns a null node.


##### Use of Memory and Time
A sample program to test the implementation discussed here is available as Listing 2 from the Linux Journal FTP site (ftp.linuxjournal.com/pub/lj/listings/issue129/6828.tgz). On my Pentium II (349MHz, 32MB of RAM and 512KB of level 2 cache), when I run the pointer distance implementation, it takes 15 seconds to create 20,000 nodes. This is the time needed for the insertion of 20,000 nodes. Traversal and deletion of the whole list does not take even a second, hence the profiling at that granularity is not helpful. For system-level implementation, one might want to measure timings in terms of milliseconds.

When we run the same pointer distance implementation on 10,000 nodes, insertion takes only three seconds. Traversal through the list and deletion of the entire list both take less than a second. For 20,000 nodes the memory being used for the whole list is 160,000 bytes, and for 10,000 nodes it is 80,000 bytes. On 30,000 nodes it takes 37 seconds to run the insertion. Again it takes less than a second to finish either the traversal or the deletion of the whole list. It is somewhat predictable that we would see this kind of timing, as the dynamic memory (heap) used here is being used more and more as the number of nodes increases. Hence, finding a memory slot from the dynamic memory takes longer and longer in a nonlinear, rather hyperlinear fashion.

For the conventional implementation, the insertion of 10,000 nodes takes the same three seconds. Traversal takes less than a second for both forward and backward traversal. Total memory taken for 10,000 nodes is 120,000 bytes. For 20,000 nodes, the insertion takes 13 seconds. The traversal and deletion individually takes less than a second. Total memory taken for 20,000 nodes is 240,000 bytes. On 30,000 nodes it takes 33 seconds to run the insertion and less than a second to run the traversal and the deletion. Total memory taken by 30,000 nodes is 360,000 bytes.


##### Conclusion
A memory-efficient implementation of a doubly linked list is possible to have without compromising much timing efficiency. A clever design would give us a canonical set of primitive operations for both implementations, but the time consumptions would not be significantly different for those comparable primitives.


----------------------------------------------------------------------------------------------------------------------
 ### References:
 * 