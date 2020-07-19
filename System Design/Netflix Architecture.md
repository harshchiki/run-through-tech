Netflix Architecture

30/09/2019 21:15**[Work In Progress]**
**Scale**:

- 250 million hours of video per day
- 98 million paying subscribers
- 190 countries.

**Scaling Culture**:

- Loyalty is GOOD
    - stabiliser
    - People who have been stars for us, and hit a bad patch, get a near term pass, because we think they are likely to become stars for us again.
    - If Netflix hits a temporary bad patch, we want people to stick with us.
    - But, unlimited loyalty to a shrinking firm, or to an ineffective employee, is not what we are about.
- The best managers figure out how to get great outcomes by setting the appropriate context, rather than by trying to control their people.
- More in slides - [8]

**50K feet view:**

    ![Screen Shot 2019-06-02 at 6.10.53 AM.png](../_resources/ec06cc643569b7bbaf87fee3eedd1073.png)

**Supporting multiple devices:**

- The huge amount of codec and bitrate combinations on Netflix means “having to encode the same title 120 different times before it can be delivered to all **streaming platforms**”.
- Although Netflix uses **adaptive bitrate streaming technology** to adjust the video and audio quality to match the customer’s download speed, they also provide users the ability to choose the quality of video on its website.
- They support every title in the following Codecs with different bit rates to make them work on device and connection.
    - Video – [VC-1](https://en.wikipedia.org/wiki/VC-1), [H.264 (AVC)](https://en.wikipedia.org/wiki/H.264), VC-1, [H.263](https://en.wikipedia.org/wiki/H.263), [H.265 (HEVC)](https://en.wikipedia.org/wiki/High_Efficiency_Video_Coding)
    - Audio – [WMA](https://en.wikipedia.org/wiki/Windows_Media_Audio), [Dolby Digital](https://en.wikipedia.org/wiki/Dolby_Digital), [Dolby Digital Plus](https://en.wikipedia.org/wiki/Dolby_Digital_Plus), [AAC](https://en.wikipedia.org/wiki/Advanced_Audio_Coding) and [Ogg Vorbis](https://en.wikipedia.org/wiki/Ogg_Vorbis)

**Netflix OpenConnect CDN**:

- Open Connect Video: [https://www.youtube.com/watch?v=mBCXdaukvc](https://www.youtube.com/watch?v=mBCXdaukvcc)

**Netflix Ecosystem:**

- 100s of microservices
- 1000s of daily production changes
- 10000s of instances
- 100000s of customer interactions per minute
- 1000000s of customers
- 1000000000s of metrics
- 10000000000 hours of content streams
- **10s of operations engineers****.**

**In a nutshell, this is what happens when you click “Play”:**

- Hundreds of micro services or tiny independent programs, work together to make one large Netflix service.
- Content legally acquired or licensed is converted into a size that fits your screen, and protected from being copied.
- Servers across the world make a copy of it and store it so that the closest one to you delivers it at max quality and speed.
- When you select a show, your Netflix app cherry picks which of these servers will it load the video from.

**Netflix Play API : Evolutionary Architecture (ref: [11])**

- Fundamental requirements of the system
    - Lead the Internet TV revolution to entertain billions of people across the world
    - Maximise customer engagement from signup to streaming.
    - Enable acquisition, discover, playback functionality 24/7
- Previous Architecture
- **![Screen Shot 2019-06-02 at 9.57.15 AM.png](../_resources/1ff98a4c3678f10096d02fa0c369b84b.png)

**

- API Proxy Service: protocol termination + monitoring. + routing
- API Services would have:
    - Signup workflow: Signup API communicating with Sign-up micro-service
    - Content Discovery Workflow: Discovery API communicating with Content Discovery micro-service.
    - Playback workflow: Play API communicating with Playback micro-service.
- API Identity
        - Start with WHY: Why does your service exist?
        - Why does your service exist w.r.t to the existence of your company (key functional requirements, as stated above), w.r.t other services. (#Cloud)
    - Deliver acquisition(signup), discovery and playback functions with HIGH AVAILABILITY.
    - SRP : Be wary of multiple identities rolled up into a single service.
    - Architecture evolution:
    - ![Screen Shot 2019-06-02 at 10.18.38 AM.png](../_resources/27f01077591089953712ee78524ff1e0.png)
    - Playback workflow
    - ![Screen Shot 2019-06-02 at 10.21.41 AM.png](../_resources/b758453445e9bd173cd269c642b29c85.png)
        - Eventually, they identified **high coupling** with the various roles various micr-services were performing.
        - SO, Playback API Identity: Orchestrate Playback Lifecycle with stable abstractions.
    - Guiding Principle for API Identity:
        - Simple singular identity for our services (like playback proxy in case shown above)
        - The identity relates to and complements the identities of the company, organisation, team and its peer services.
- **Type 1/2 Decisions**
    - **Type 1: **
        - **Thee are consequential and irreversible or nearly irreversible - one way doors - and these decisions must be make methodically, carefully, slowly and create deliberation and consultation. (Quote from Jeff Bezos, Amazon CEO)**
    - **Type 2:**
        - **But most of the decisions are not “Type 1"**
        - **Type 2 are changeable, reversible and they’re 2 way doors.**
        - **If you make a sub-optimal Type 2 decision, you don’t have to live with the consequences for that long.**
        - **They can and should be Makde quickly by high judgement individuals or small groups. (Quote from Jeff Bezos, Amazon CEO)**
- Type 1 decisions at Netflix:
    - Appropriate coupling
    - Synchronous & asynchronous
    - Data Architecture
- Previous architecture:
        - ![Screen Shot 2019-06-02 at 10.37.55 AM.png](../_resources/a54ce07c97ac6a32100e26eb8295bcd7.png)
    - Distributed monolith is far worse than centralised monolith
    - **The evils of too much coupling between services are far worse than the problems caused by code duplication. (Same Neumann - Building Micro-services)**
        - ![Screen Shot 2019-06-02 at 11.49.02 AM.png](../_resources/a6dbac0da8063966a08b4e24904f3033.png)
    - **Operational Coupling (Problem with shared libraries)**: In the above drag, we have a Playback decision client to access Playback decision service. The client previously had a fall back mechanism if the Playback decision service was down. Unfortunately it was observed that the fallback mechanism was so CPU intensive (in one instance) and causing the latency of Play API service to go over the roof. So impacting the performance (reliability and availability) of Play API service.
        - The operational coupling can established by the fact that, the playback decision client of the Playback decision service is leaking into the Play API service.
        - Operational coupling could be an ok choice, if some services/teams are not yet ready to own and operate a highly available service.
        - Over years, though, operational coupling impacts availability.
    - **Language coupling (Problem with shared libraries)**
    - **Communication Protocol (Problem with shared libraries)** - For e.g. one API made to communicate with other (developed using Jersey framework, REST API), and the consumer API is bound to make calls over HTTP/1.1, and thus only uni-directional flow.
- **With the above problems, they came with 4 requirements**:

    1. **Operationally “thin” clients:**

        - Whatever client libraries we are incorporating, it should:
            - Not have any heavy fallbacks.
            - No special logic
            - Dependencies which bring it, should be well defined and limited.

    2. **No or limited shared libraries**.
    3. **Auto generated clients for Polyglot support**

        - Some kind of tooling to generate thin clients in multiple languages.

    4. **Bi-directional communication**

        - To explore communication beyond request and response style of communication.
- REST vs RPC
    - At Netflix, most use-cases were modelled as Request/Response
        - REST was a simple and easy way of communicating between services, so choice of REST was more incidental rather than intentional.
    - Most of the services were not following RESTful principles
        - The URL didn’t represent a unique resource, instead the parameters passed in the call determined the response - effectively making them an RPC call.
    - So, Netflix was agnostic to REST over RPC, as long as it met their requirements.
    - **So came up with gRPC.**
        - **![Screen Shot 2019-06-02 at 12.12.50 PM.png](../_resources/52432d84bbc822af72c4c1eb88f0ed93.png)

**

    - Points about the new architecture:
        - **Type 1 decision: Appropriate coupling**
            - Consider “thin” auto generated clients with bi-directional communication and minimise code reuse across service boundaries.
        - **Type 1 decision: Synchronous vs asynchronous**
            - Typical synchronous architecture
                - ![Screen Shot 2019-06-02 at 12.16.52 PM.png](../_resources/e03c632b1b55df0c9f6d9d2f2b0e0732.png)
                - Request Handler Thread Pool (**Blockin****g Request Handler**): A dedicated thread pool for each incoming request.
                - The orchestrator API invoke each client to talk to the micro-service they are a client of, over a defined thread pool (per client **Blocking Client I/O**)
                - Above e.g. shows getPlayData, which requires customer information, device information, to decide on the play data.
                - (Partly cons): Works for simple request/response style API
                - (Partly cons): Works for limited clients.
            - **Beyond Request/Response**
                - **One request - on response**
                - **One request - stream response**
                - **Stream request - one response**
                - **Stream request - stream response**

![Screen Shot 2019-06-02 at 12.22.23 PM.png](../_resources/94144c0c0e30fe130738839e0be69e2c.png)

                - **Whenever you see element of stream (in request or response) it is worth considering an asynchronous architecture.**
            - **Asynchronous Architecture**
                - Worker threads is a function of the number of cores in the underlying machine.
                - ![Screen Shot 2019-06-02 at 12.29.24 PM.png](../_resources/61659ea0bc20711f1884dce408e65dc7.png)

PLayData getPlayData(String customerId, String titleId, String deviceId) {
    Zip(getCustomerInfo(cusomterId),getDeviceInfo(deviceId),
       (custInfo, deviceInfo) ->
            return decidePlayData(custInfo, deviceInfo, titleId)
       );
}

                - Above shown code is tweaked to run in asynchronous fashion, such that, fetching customer info and fetching device info, happen in parallel, and once both are fetched the play data can be decided on.
                    - Essentially different worker threads fetching customer info and device info, then another worker thread for deciding on the play data.
                - **Workflow spans multiple threads:**
                    - All context is passed as messages from one processing unit to another.
                    - If we need to follow and reason about a request, we need to build tools to capture and reassemble the order of execution units.
                    - None of the calls can block.
                - Essentially:
                - ![Screen Shot 2019-06-02 at 12.35.38 PM.png](../_resources/fef4aadc161e76a3384d909ee5eb361f.png)
            - **Guiding Principle: **So, the ask is, **do we really have a need beyond Request/Response?**
                - **In context to what happens synchronously (blocking) and what asynchronously(non-blocking), it is crucial to identify what are the Type 1 and Type 2 decisions.**
                - **So, the incoming and outgoing I/Os were made non blocking, and kept the actual request processing blocking.**
            - **Type 1 Decision: Synchronous vs asynchronous**
                - **If most of your APIs file the Request/Response pattern, consider a synchronous request handler, with nonblocking I/O.**
        - **Type 1 Decision: Data Architecture**
            - Data monolith:
                - Earlier we have data service : Services mapping :: m:n
                - It was observed that a lot more than required data was loaded into memory (and thus consuming resources) in this architecture.
                - Each data source got coupled across classes and libraries.
                - It was also observed that whenever there was a data update, the CPU utilisation went up, GC pressure increased => so, increase in latencies.
                    - So performance characteristics could not be defined due to this variable behaviour on data updates.
                - Idea so then was to decouple API/services and the data sources - as much as the could.
            - Solution/Improvement:
                - ![Screen Shot 2019-06-02 at 1.43.25 PM.png](../_resources/81c2f382c28702e5d2367b521df10897.png)
                - A (logically) central data loader was brought in place, which as per request from the service, would create a materialised view, having only the data that is required, and then store them in the data store, which the service could use for its purpose.
                - Data Service was the abstraction.
                - Benefits:
                    - Uses only the data it needs
                    - Predictable operational characteristics.
                    - Reduced Dependency chain.
                        - Because of the introduced abstraction (Data Service) dependency depth reduced (could be well defined).
                    - Since not more than required data was then stored in memory - operation characteristics + performance boost + GC pressure low + scalable (of course)
            - **Guiding Principle:**
                - **Isolate Data from the service.**
                - **At the very least, ensure that data sources are accessed via a layer of abstraction, so that it leaves room for extension later (scalability)**
    - **For Type 2 decisions, choose a path - experiment and iterate.**
    - **Guiding Principle:**
        - **Identity your Type 1 and Type 2 decisions; Spend 80% of your time debating and aligning on Type 1 decisions.**
- **Evolvability:**
    - **An ****Evolutionary Architecture**** supports ****guided**** and ****incremental change**** as ****first principle**** ****among multiple dimensions (evolve across multiple dimensions).**
    - Choosing micro services architecture with appropriate coupling allows us to evolve across multiple dimensions.
    - The current architecture provisions the adaptability of an asynchronous framework and build observability tools as all one needs to do to enhance the system/service.
    - With the current architecture we have the following aspects evolvable (**Type 1 decisions**) - known unknowns
        - Asynchronous
        - Polyglot services - Having tooling to generate “thin” clients of services, for vapours languages - so language coupling goes away.
        - Bidirectional APIs
        - Additional Data Sources - Having a layer of abstraction helps achieve this, while being able to clearly define the dependency depth, memory usage capped, GC pressure in control.
    - Potential Type 1 decisions in the future - unknown unknowns
        - Containers?
        - Serverless?
    - As we evolve, how to ensure we are not breaking our original goals?
        - General goals
            - High availability
            - Low latency
            - Simplicity
            - Reliability
            - High throughput
            - Developer Productivity
            - Observability
            - Continuous Integration
            - Scalable
            - Evolvability
                - Whats more important is how each of the above goals work with other goals, and which precede which based on use-case/requirement.
                - For. e.g.
                    - Simplicity takes over Reliability - complex/heavy fallback logics.
                    - Scalability over throughput - while cache helped us boost throughput, in a situation when we had to horizontally scale our services, we noticed we had to have some kind of cache warming up process for the newly spawned nodes to work as expected, which proved cumbersome and resulted in errors.
                    - Observability over latency
                        - Cost of async: loss of observability.
                        - Decrease in latency by using a fully async executor was observed.
                        - So given the choice, observability was preferred.
- 4 9’s of availability
    - Fitness functions
        - ![Screen Shot 2019-06-02 at 2.59.41 PM.png](../_resources/39c01dc9e85a7f6427da0bc809547d68.png)
        - Tests developed based on above drag - health check - fitness tests for availability.
        - **Guiding Principle:**
            - **Define Fitness functions to act as your guide for architectural evolution.**
- Quickly summing up the previous and improved/current architecutre:
    - ![Screen Shot 2019-06-02 at 3.01.23 PM.png](../_resources/8c5a7611bde5d61a9504e1fa0d0ed32b.png)

    -

**Netflix Movie Recommendation Algorithm**

- Ref: [10] https://www.quora.com/How-does-the-Netflix-movie-recommendation-algorithm-work
- Rating prediction is not the primary concern.
- 2 primary algorithms used:
    - Restricted Boltzman Machines (RBM)
        - Some kind of **fancy neural networks**
        - Help play in some tricks with collaborative filtering.
        - Research paper: https://www.cs.toronto.edu/~rsalakhu/papers/rbmcf.pdf
    - a form of Matrix Factorization.
        - Research paper: http://faculty.cse.tamu.edu/huangrh/Spring16/papers_course/matrix_factorization.pdf
- There are many other recommendation algorithms from personalised ranking to page optimisation that make up Netflix Recommendation System.
- More references:
    - Part 1: https://medium.com/netflix-techblog/netflix-recommendations-beyond-the-5-stars-part-1-55838468f429
    - Part 2: https://medium.com/netflix-techblog/netflix-recommendations-beyond-the-5-stars-part-2-d9b96aa399f5
    - Some slides: https://www.slideshare.net/xamat/kdd-2014-tutorial-the-recommender-problem-revisited

**Technologies used (ref [0])**
![Screen Shot 2019-06-02 at 5.51.10 AM.png](../_resources/5c244a604a25cc443b886eb3e370bc7f.png)

* * *

**References:**

[0] Highscalability: http://highscalability.com/blog/2015/11/9/a-360-degree-view-of-the-entire-netflix-stack.html

[1] https://medium.com/refraction-tech-everything/how-netflix-works-the-hugely-simplified-complex-stuff-that-happens-every-time-you-hit-play-3a40c9be254b

[2] InfoQ: Netflix Play API : Building an evolutionary architecture: https://www.infoq.com/news/2019/01/netflix-evolution-architecture/

[3] nginx dot com : Adopting Microservices at Netflix: Lessons for Architectural Design : https://www.nginx.com/blog/microservices-at-netflix-architectural-best-practices/

[4] https://conferences.oreilly.com/software-architecture/sa-ny-2018/public/schedule/detail/63771

[5] Netflix tech blog: https://medium.com/@NetflixTechBlog

[6] Netflix official blog: https://medium.com/netflix-techblog

[7] Stack behind Netflix scaling : http://www.scalescale.com/the-stack-behind-netflix-scaling/#

[8] Slides on Netflix scaling culture : https://www.slideshare.net/reed2001/culture-1798664

[9] Evolution of open source at Netflix : https://medium.com/netflix-techblog/evolution-of-open-source-at-netflix-d05c1c788429

[10] Netflix movie recommendation algorithm : https://www.quora.com/How-does-the-Netflix-movie-recommendation-algorithm-work

[11] Evolutionary architecture: https://qconsf.com/system/files/presentation-slides/qcon_netflix_play_api.pdf

- Video: https://www.youtube.com/watch?v=6oPj-DW09DU

[12] GRPC: https://grpc.io/