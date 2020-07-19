# Event-driven architectures

----------------------------------------------------------------------------------------------------------------------

**Event drive Architectures**

- Programming models have evolved:
    - Distributed Object (RPC sync)
    - SOA
    - Enterprise Service Bus (ESB)
    - Reactive Programming
    - Microservices

-

- All developers, architects, and product managers are used to REST APIs and the synchronous paradigm of communication. You make a request and wait for the response. This is exactly how the web works. You enter a URL (e.g., google.com) in the address bar of your favorite browser and it sends a request to the server. Following, the server sends the response with the content of the website. The web is the greatest implementation of a REST API.
- However, **there are certain situations when you don’t really need a response from the server.** At least no other than the confirmation the request has been received. This is also called “fire and forget”, and it’s really useful when you just want to communicate or inform that “something happened.” It is, you’re not requesting or asking for anything, thus you don’t need a response. **Examples** of this are:
    - A user just signed up.
    - You have a new follower.
    - Your fridge is getting empty.

Along with the event, you may also want to send extra information. For instance:

- A user just signed up: here’s the user information (e.g., name, email, age, etc.)
- You have a new follower: here are the details of the follower (e.g., username, name, picture, etc.)
- Your fridge is getting empty: here’s the percentage of “emptiness” (e.g., 23%)

This extra information is often referred to as event payload or message payload.

##

##

## Core concepts[#︎](https://www.asyncapi.org/docs/getting-started/event-driven-architectures/#core-concepts)

![](../_resources/9729784f790fa7a20cdc036ebe2958f5.png)

**In most cases, Event-Driven Architectures (EDAs) are broker-centric**, like in the diagram above. In it you can find some new concepts, so let’s go through them now.

### Message broker[#︎](https://www.asyncapi.org/docs/getting-started/event-driven-architectures/#message-broker)

A message broker (or “broker”) is a piece of infrastructure** in charge of receiving messages and delivering them to those who have shown interest**. They often store messages until they are delivered, what makes EDAs very resilient to failures. Examples of brokers are [RabbitMQ](https://rabbitmq.com/), [Apache Kafka](http://kafka.apache.org/), [Solace](http://solace.com/), etc.

### Publisher/Subscriber[#︎](https://www.asyncapi.org/docs/getting-started/event-driven-architectures/#publisher-subscriber)

A publisher (a.k.a. producer) is an application that sends messages to the broker.

A subscriber (a.k.a. consumer) is an application that connects to the broker, manifests interest in certain type of messages, and leaves the connection open so the broker can push messages to them.

### Message[#︎](https://www.asyncapi.org/docs/getting-started/event-driven-architectures/#message)

A message is a piece of information that’s sent by the publishers to the broker, and received by all the interested subscribers. The content of the message can be anything but they are frequently catalogued as events and commands. As we saw above, events communicate a fact that occurred. Instead, commands are very much like requests in REST APIs: they tell the subscribers “do this”.

Technically speaking, events and commands are the same. The only difference is in their semantics.

### Channels[#︎](https://www.asyncapi.org/docs/getting-started/event-driven-architectures/#channels)

One detail that might pass unnoticed from the diagram above is the existence of channels. All the brokers support communication through multiple channels. The industry doesn’t have a common term though so you may find them as topics, routing keys, event types, and probably other ones I’m missing.

They’re usually assigned a name or identifier (e.g., user_signed_up) and it’s often a good practice to send a single type of message through them. Think about TV or radio channels: the BBC only broadcasts its information through an assigned channel. If the broadcasters (publishers) didn’t respect that rule you (the subscriber) would only see and hear interferences.

## Why “event-driven” and not “message-driven”?[#︎](https://www.asyncapi.org/docs/getting-started/event-driven-architectures/#why-event-driven-and-not-message-driven)

You will find both used interchangeably, although they are not exactly the same. You will even find “message-based” and “event-based”. In practice, chances are they all refer to the same thing.

Theoretically, “message-driven” is the most generic term, meaning you may use events and commands, while event-driven means that it’s purely about events. However, that’s not always the case, as Martin Fowler explains in his talk “the many meanings of event-driven architecture”:

Link to the talk from Martin Fowler : https://www.youtube.com/watch?v=STKCRSUsyP0

* * *

**Events - Why do they matter?**

- Idea is to think everything behaving **asynchronously**.
- The value of events is that a **sequence of related events represent behavior **(e.g., an item was added and then removed from a shopping cart, an error recurs every 24 hours or users always clicks through a site in a particular order).
- A sequence of related events is commonly called a stream. Streams can come from an IoT device, a user visiting a website, changes to a database or many other sources. When thinking of APIs, we start with the event rather than the highly coupled concept of a command, e.g., an event-X has **occurred**, rather than command-Y should be **executed**. This thinking underpins event-driven streaming systems.
- ![](../_resources/2a32cf9c304054bd31ba4fce05dcfde6.png)
- **Fundamentally in real world an “event” can be described as:**
    - **Atomic: something happened**
    - **Related to: a stream/sequence of events**
    - **Behavioural: accumulation of facts capture the behaviour.**

**Considerations of the event driven architectures**

- Events initially start out as atomic, and drive reactionary callbacks (functions)
- **Fundamental: An event represents a fact.**** It is immutable**. Therefor changes how we think about a domain model.
- **Problem with events:** Events are scattered around the system. They are scattered everywhere, and the lack of consistency makes them very difficult to work with at scale.
- Events do not exist in isolation.
    - Due to their very nature, they tend to be part of a flow of information, a stream.

**Note: Event first thinking changes how you think about what you are building.**

**
**

How is the event treated within a system?** (Patterns of events based systems)**

- Is it **observable**, and are the **flows of streams** behaving as expected?
- Is it trusted? Meaning **transactional**, **exactly once or at least once**? Will it **scale**?
- Is **stateless processing,** such as **filtering**, **projection**, **cleaning **or **enrichment**  **required**? Is stateful processing, such as **aggregations** or **stateful sequence processing required**?
- **Is a materialised view against the stream required? How many transactions per second are required for a windowed view?**
    - Do we want to **scale/fan/map out (parallelise), fan in/collect, and build materialised views**?
- Does it support **error handling**, such as **error flows** and dead letter queue?
- Does it send and transform events from **one stream into others (stream processing)**?
- Does it** react and drive intelligence **from the state collection from a stream (**stream processing**)?

Above patterns of events based systems, following fall under the suitable category to be built as event driven systems (**REAL TIME**):

- Data pipelines (ETL, integration)
- Monitoring alerting
- Enterprise wide event drive micro services based platform
- IoT
- Central nervous system

**Being event driven**

- There are several ways of being event driven. For example:
    - Throwing an event **item-purchased** as something that “has happened”.
    - OR, sending out a command as an event -> **user.purchase(item)**.
- In the above 2 examples, both are events, but the design principles are fundamentally different.
- The importance of events and event first thinking:
    - Capture **facts**
    - Capture **behaviour**
    - Provide a **representation of the real world**.
    - **Model** use cases of **how we think**.
    - Supports** repeated evaluation and processing** (a time machine)
    - Provide** horizontal scaling**
    - **Speak the same language as the business**.

**Event-first vs event-command patterns for event driven design**

- Event-first approach is generally recommended. Why?
    - Models real world better without adding clutter of API/tech involved. Having command in, involves an API.
    - **Event-first analog**: I walk into a room. Generate **“entered room”** event and the light turns on.
        - I don’t need to worry at all about how the light is switched on, which API to call.
        - The sensor detects my presence and it has the responsibility to switch on the light.
        - The sensor “observes” on the events generated in the room (that it has subscribed to)
    - **Event-command analog**:I walkt into the room. I invoke a command **“flip the light switch”** and the light turns on.
        - Knowledge of API is required to have the transaction execute well.
- **The event-first approach forces an inversion of responsibility; it is a fundamental paradigm shift of how applications are developed.**

**Event-command pattern**

- The service knows the endpoints and API of other services and calls them.

![](../_resources/05cacb982698c3ab8879677c957fee15.png)

**Event-first pattern**

- Each service listens to inbound events and output >=0 events after performing some (re)action.

![](../_resources/32497aab4f09795856725c102956d27f.png)

- Looks more complex. There is a lot of publishing and consuming happening.
- **Cost**:
    - The tradeoff of the event-first/event-driven architecture is t**he amount of scaffolding required to inspire confidence**. In the old days when [transactional consistency](https://en.wikipedia.org/wiki/Consistency_(database_systems)) shaped our expectations, and NoSQL pushed us to eventual consistency with [CAP theorem](https://en.wikipedia.org/wiki/CAP_theorem) tradeoffs,** it was known that data wouldn’t (and shouldn’t) get lost. Event orientation requires not only different thinking in replacing the command model but also the ability to support traceability, failure paths, scaling and explanation about why things have gone wrong**.
- **Benefits**:
    - Decoupling
    - Encapsulation: There are clear boundaries between processors.
    - Inverted knowledge
    - Evolutionary change: System and events can change over time.
    - Event sourcing

* * *

References:

- WIP:
    - Part 1: https://www.confluent.io/blog/journey-to-event-driven-part-1-why-event-first-thinking-changes-everything
    - Part 2: https://www.confluent.io/blog/journey-to-event-driven-part-2-programming-models-event-driven-architecture
    - Part 3: https://www.confluent.io/blog/journey-to-event-driven-part-3-affinity-between-events-streams-serverless
    - Part 4: https://www.confluent.io/blog/journey-to-event-driven-part-4-four-pillars-of-event-streaming-microservices
- TODO: https://www.confluent.io/blog/build-services-backbone-events/
- To Read: https://www.thoughtworks.com/books/building-evolutionary-architectures
- Martin Fowler Article: https://martinfowler.com/eaaDev/EventNarrative.html