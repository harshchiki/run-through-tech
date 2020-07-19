# Java - Loading Linking Initialization (Java 11)
* Reference: <https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-5.html>

# Loading
* Loading is the process of finding the binary representation of a class or interface type with a particular name and creating a class or interface from that binary representation. 

# Linking
* Linking is the process of taking a class or interface and combining it into the run-time state of the Java Virtual Machine so that it can be executed. 

# Initialization
* Initialization of a class or interface consists of executing the class or interface initialization method [clinit](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-2.html#jvms-2.9.2)

# Run time constant pool
* JVM maintains a run-time constant pool for each class and interface 
    * [Run time constant pool](https://docs.oracle.com/javase/specs/jvms/se11/html/jvms-2.html#jvms-2.5.5)
        * Per class, per interface run time representation of the `constant_pool` table in a class file.
          * contains:
              * several kinds of constants: ranging from numeric literals known a compile time to method and field references that must be resolved at run time.
          * The run time constant pool serves a function similar to that of a symbol table for a conventional programming language, although it contains a wider range of data than a typical symbol table.
          * **Where is it allocated?**
            * Each run time constant pool is allocated from JVM's method area
          * **When is it created?**
            * when the class or interface is created by the JVM
          * **IMP:** **When creating a class or interface, if the construction of the run-time constant pool requires more memory than can be made available in the method area of the Java Virtual Machine, the Java Virtual Machine throws an OutOfMemoryError.**
        * 2 types of entries in the run time constant pool:
            * symbolic references: which may later be resolved
            * static constants: which require no more processing.



# JVM Setup
* JVM starts by creating an initial class or interface using the **bootstrap class loader** or a user defined class loader.
* JVM then links the initial class or interface, initializes it and invokes the `public static void main(Stringp[ args])`
    * The invocation of this method, drives all further execution.
    * execution of JVM instructions constituting the `main` method may cause linking (and consequently creation) of additional classes and interfaces, as well as invocation of additional methods.


# Creation and Loading
* **What does creation mean?**
    * Creation of a class or interface `C` denoted by name `N` consists of the construction in hte method area of the JVM of an implementation specific internal represenation of `C`
* 2 kinds of class loaders:
    * Bootstrap class loader (supplied by JVM)
    * User defined class loader
        * Every user defined class loader is an instance of a subclass of the abstract class `ClassLoader`.
        * **Why are user defined class loaders used?**
            * Applications employ user-defined class loaders in order to extend the manner in which JVM dynamically loads an thereby creates classes.