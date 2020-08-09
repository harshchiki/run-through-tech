package samples.guavacache.lru;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/*
 * This Cache implementation is wrapper over Google Guava Cache implementation.
 *
 * Eviction policy : LRU (implemented by eviction by size of cache)
 *
 * Note: The key queried for in "get", "removes" it from the cache, as this cache implementation
 * is tightly bound with the cache that holds the value objects.
 */
public class LRUVariant1<K, V> {
    private Cache<K, V> cache;

    @SuppressWarnings("unchecked")
    LRUVariant1(final Long maxSize, final int concurrencyLevel) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize) // LRU behaviour when max size is reached
                .concurrencyLevel(concurrencyLevel)
                .removalListener(new RemovalListener() {
                    public void onRemoval(RemovalNotification removedItem) {
                        System.out.println("[CACHE] EVICTION key: "
                                + removedItem.getKey()
                                + ". Current cache size: "
                                + cache.size());
                    }
                })
                .build();
    }

    void put(final K key, final V value) {
        cache.put(key, value);
    }

    V getAndRemove(final K key) {
        final V value = cache.getIfPresent(key);
        if(null != value) {
            System.out.println("[CACHE] Sucessful get and then removal of " + key);
            cache.invalidate(key);
        } else {
            System.out.println("[CACHE] MISS:      " + key);
        }
        return value;
    }

    void invalidateCache() {
        System.out.println("cache stats at invalidation: "
                + "\n  size: " + cache.size()
                + "\n  hitCount: " + cache.stats().hitCount()
                + "\n  missCount: " + cache.stats().missCount()
                + "\n  evictionCount: " + cache.stats().evictionCount()
                + "\n  requestCount: " + cache.stats().requestCount()
                );
        cache.stats().minus(cache.stats());
        cache.invalidateAll();
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Java: "  + System.getProperty("java.runtime.version"));
        /*************** CONSTANTS START ************** */
        final Long MAX_SIZE =50L;
        final int CONCURRENCY_LEVEL = 2;
        
        final int NO_OF_TASKS = 100;
        
        final int PRODUCER_WAIT_TIME = 2;
        final int CONSUMER_WAIT_TIME = 4;
        /*************** CONSTANTS END ************** */
        
        
        final ExecutorService producer = Executors.newSingleThreadExecutor();
        final ExecutorService consumer = Executors.newSingleThreadExecutor();
        
        final LRUVariant1<String, String> cache = new LRUVariant1<>(MAX_SIZE, CONCURRENCY_LEVEL);
        
        final List<Callable<String>> producerTasks = new ArrayList<>();
        // producer
        for(int i = 0; i < NO_OF_TASKS; i++) {
            final Integer count = new Integer(i);
            producerTasks.add(new Callable<String>() {
                @Override
                public String call() {
                    try {
                        Thread.sleep(PRODUCER_WAIT_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cache.put("Key " + count, "Value " + count);
                    System.out.println("[PRODUCER TASK] CACHE ADD: Key " + count);
                    return "Key " + count;
                }
            });
        }
        
        final List<Callable<String>> consumerTasks = new ArrayList<>();
        // consumer tasks
        for(int i = 0; i < NO_OF_TASKS; i++) {
            final Integer count = new Integer(i);
            consumerTasks.add(new Callable<String>() {
                @Override
                public String call() {
                    try {
                        Thread.sleep(CONSUMER_WAIT_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[CONSUMER TASK] CACHE RETRIEVAL: Key " + count);
                    return cache.getAndRemove("Key " + count);
                }
            });
        }
        
        System.out.println("\n\n*******************************************");
        System.out.println("\n\n********* RETRIEVING FROM CACHE ***********\n");
        final long consumerStart = System.currentTimeMillis();
        final List<Future<String>> consumerHandles = consumer.invokeAll(consumerTasks);
        System.out.println("\n*******************************************");
        System.out.println("\n********* ADDING TO CACHE *****************\n");
        final long producerStart = System.currentTimeMillis();
        final List<Future<String>> producerHandles = producer.invokeAll(producerTasks);
        System.out.println("*******************************************");
        
        
        producerHandles.forEach(handle -> {
            try {
                System.out.println("[PRODUCER] Submit complete: " + handle.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        long producerEnd = System.currentTimeMillis();
        System.out.println("\n\n********* ADDED ALL TO CACHE **************");
        System.out.println("\n*******************************************");
        
        final AtomicInteger countConsumer = new AtomicInteger(0);
        consumerHandles.forEach(handle -> {
            try {
                int i = countConsumer.getAndIncrement();
                System.out.println("[CONSUMER] Consuming: \"Key " + i + "\"");
                
                Optional.ofNullable(handle.get()).ifPresentOrElse(
                        val -> System.out.println("[CONSUMER] Value for \"Key " + i + "\" = \"" + val + "\""),
                        () -> System.out.println("[CONSUMER] No value for \"Key " + i + "\""));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        long consumerEnd = System.currentTimeMillis();
        System.out.println("\n********* RETRIEVED ALL FROM CACHE ********");
        System.out.println("\n\n*******************************************");
        
        System.out.println("Producer duration " + (producerEnd - producerStart) + "ms");
        System.out.println("Consumer duration " + (consumerEnd - consumerStart) + "ms");
        System.out.println("*******************************************");
    }
} 