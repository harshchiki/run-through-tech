## Strings in Java 9 and onwards
Until Java 8, Strings were internally represented as an array of characters – char[], encoded in UTF-16, so that every character uses two bytes of memory.

With Java 9 a new representation is provided, called Compact Strings. This new format will choose the appropriate encoding between char[] and byte[] depending on the stored content.

Since the new String representation will use the UTF-16 encoding only when necessary, the amount of heap memory will be significantly lower, which in turn causes less Garbage Collector overhead on the JVM.

Ref: <https://www.baeldung.com/java-string-pool#a-note-about-java-9>