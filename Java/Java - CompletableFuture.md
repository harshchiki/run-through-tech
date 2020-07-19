# General notes on `Future`
* A `.get()` on a "future" instance -> "throws" `ExecutionException` -> which basically wraps the exception that occurred during the computation.
* `InterruptedException` (an exception signifying that a thread executing a method was interrupted)




# Using CompletableFuture as a Simple Future

```java
public Future<String> calculateAsync() throws InterruptedException {
    CompletableFuture<String> completableFuture  = new CompletableFuture<>();
    
    Executors.newCachedThreadPool().submit(()->{
        Thread.sleep(500);
        completableFuture.complete("Hello");
        return null; 
    });
    
    return completableFuture;
}
```


# Non blocking Future
Below usage: <u>**`get` will never block**</u>.
```java
Future<String> completableFuture = calculateAsync();
System.out.println(completableFuture.get());
```

# Cancelling CompletableFuture
```java
public Future<String> cancel() {
    CompletableFuture<String> completableFuture = new CompletableFuture<>();
    
    Executors.newCachedThreadPool().submit(() -> {
        Thread.sleep(500);
        completableFuture.cancel(true);
        return null;
    });
    
    return completableFuture;
}
```
The `get` calls throws `java.util.concurrent.CancellationException` in this case.
