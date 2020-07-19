# Notes on System design

## Distributed Consensus 
<p>
----------------------------------------------------------------------------------------------------------------------

With distributed consensus, different legal entities can have a master but remain in consensus. Three conditions are critical to support this scenario:

* All parties controlling an instance of a database need to come to an agreement on the order of transactions, and commit the transactions to the database in that order.
* No single party should be able to change or influence the order of transactions. This is a concept called immutability.
* Stopping the transactions across the community is impossible.

Process of consensus follows four steps:

* Each node creates the transactions it wants to record.
* The data is shared between the nodes (an obvious and critical step).
* Consensus is established on the order of valid transactions.
* Nodes update their transactions to reflect the consensus result.

Ref: https://hackernoon.com/distributed-ledger-consensus-explained-b0968d1ba087
<p>
----------------------------------------------------------------------------------------------------------------------



