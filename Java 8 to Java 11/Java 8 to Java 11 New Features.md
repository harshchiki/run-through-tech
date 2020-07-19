# Java 9
* ## try-with-resources
  * if the resource is referenced by a final or effectively final variable
  * Java 7
    ```
    try(final refCloseable = new MyCloseable()) {
        // do stuff with refCLoseable
    }
    ```
  * Java 9
    ```
    try(new MyCloseable().finalWrapper.refCloseable) {
        // do stuff with refCloseable
    }
    ```

* ## Interface Private method
  * We can now have private methods in interface
  ```
  public interface CustomInterface {
    private static void f1() {}

    public abstract void method1();
     
    public default void method2() {
        System.out.println("default method");
    }
     
    public static void method3() {
        System.out.println("static method");
    }
  }
  ```

* ## Publish-Subscribe Framework
  * The class java.util.concurrent.Flow provides interfaces that support the Reactive Streams publish-subscribe framework.


# Java 10

# Java 11
