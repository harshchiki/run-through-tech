# OSGi Class Loading

# Osgi and class Loading
* OSGi is a Java-specific framework that improves **the way that Java classes interact within a single JVM**. It provides the following features:
    * a modified Java classloader which provides **fine-grained control over symbolic linking with other code in the same JVM**;
    * a **central service registry for decoupling callers of an interface from the interface implementation**;
    * an **enhanced version of the java.lang.SecurityManager (ConditionalPermissionAdmin)**;
    * a large set of** standardized optional services for things like loading configuration-files, publishing events, exposing Java servlets, etc**.



# A modified Java class loader which provides fine-grained control over symbolic linking with other code in the same JVM
* It can be used without the other features of OSGi and is light weight.
* allows **internal implementation classes to be hidden from other jarfiles (mostly)**;
* allows **concurrent loading of jarfiles whose implementation happens to depend on different versions of the same library**;
* **reduces the chance that the dreaded ClassNotFoundException will occur at runtime (missing dependencies are much more obvious)**;
* **speeds up application startup (class resolution) a little**;
* **allows code in a jarfile to be executed when the jarfile is loaded**;
* **allows jarfiles to take actions when other jarfiles are loaded (self-extensible framework)**;
* **allows jarfiles to be unloaded at runtime (under some conditions)**;
* **forces bundles to include metadata about their identity and version (similar to maven pom declarations)**;
* **treats much of the JDK library like an OSGi bundle, forcing jarfiles to declare their dependencies on JDK features explicitly**.


# Note on Java Class Loading
* Refer on Harsh Google Drive - "Java - Loading, Linking, Initialization.md"


# References:
* <http://moi.vonos.net/java/osgi-classloaders/>
* Dynamic class loading: <https://developer.ibm.com/cics/2018/08/31/osgi-demystified-3-1-dynamic-class-loading/>
* What should you know about class loaders <https://blog.osgi.org/2011/05/what-you-should-know-about-class.html>
* <http://www.martinlippert.org/events/WJAX2008-ClassloadingTypeVisibilityOSGi.pdf>
* <http://www.martinlippert.org/events/WJAX2008-ClassloadingTypeVisibilityOSGi.pdf>