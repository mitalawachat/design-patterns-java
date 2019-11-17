# Structural

## Adapter

* The adapter pattern makes two incompatible interfaces compatible without changing their existing code.
* Interfaces may be incompatible, but the inner functionality should match the requirement.
* Adapter patterns use a single class (the adapter class) to join functionalities of independent or incompatible interfaces/classes.
* There are two approaches
  * `Class Adapter` – This form uses Java Inheritance and extends the source interface.
  * `Object Adapter` – This form uses Java Composition and adapter contains the source object.

### Adapter Advantages

* The adapter pattern is often used to make existing classes work with others without modifying their source code.

### Adapter Exmaples in JDK

* `java.util.Arrays#asList()`
* `java.io.InputStreamReader(InputStream)` (returns a Reader)
* `java.io.OutputStreamWriter(OutputStream)` (returns a Writer)

----

## Bridge

* The Bridge design pattern allows you to separate the abstraction from the implementation.
* There are 2 parts in Bridge design pattern:
  * Abstraction
  * Implementation
* The Bridge pattern is an application of the old advice, "prefer composition over inheritance".

### Bridge Advantages

* Bridge design pattern is useful when both abstraction and implementation can have different hierarchies independently; and we want to hide the implementation from the client application.

----

## Composite

* The Composite design pattern is a mechanism for treating individual (scalar) objects and composition of objects in a uniform manner.

### Composite Advantages

* Composite pattern is useful when when the group of objects should behave as the single object.
* Composite design pattern can be used to create a tree like structure.

### Composite Exmaples in JDK

* `java.awt.Container#add(Component)`

----

## Decorator

* Decorator design pattern facilitates the addition of behaviors to individual objects without inheriting from them.
* Decorator is used when
  * You want to augment an object with additional functionality
  * Do not want to rewrite or alter existing code (OCP)
  * Want to keep new functionality separate (SRP)
  * Need to be able to interact with existing structures
  * Two Options:
    * Inherit from required object if possible, some classes are final
    * Build aDecorator, which simply references the decorated object(s)

### Decorator Advantages

* Decorator provides runtime modification abilities
* Its easy to maintain and extend

### Decorator Exmaples in JDK

* FileReader
* BufferedReader

----

## Facade

* Facade design pattern provides a simple, easy to understand/user higher-level interface interface over a large and sophisticated body of code.
* Facade design pattern can provide a wrapper interface on top of the existing interfaces to help client application.

### Facade Advantages

* Facade design pattern can be applied at any point of development, usually when the number of interfaces grow and system gets complex.
* Client can choose whether to use Facade or not, it is more like a helper for client applications.

----

## Flyweight

* Flyweight is a space optimization technique that lets us use less memory by storing externally the data associated with similar objects.

### Flyweight Advantages

* Reduce the load on memory by sharing objects.

### Flyweight Exmaples in JDK

* String Pool implementation in Java
* Wrapper classes `valueOf()` method uses cached objects

----

## Proxy

* A Proxy is a class that functions as an interface to a particular resource.
* Types of proxy:
  * Remote Proxy:
    * Responsible for representing the object located remotely.
    * Talking to the real object might involve marshalling and unmarshalling of data and talking to the remote object. All that logic is encapsulated in these proxies and the client application need not worry about them.
  * Virtual proxy:
    * These proxies will provide some default and instant results if the real object is supposed to take some time to produce results.
    * These proxies initiate the operation on real objects and provide a default result to the application. Once the real object is done, these proxies push the actual data to the client where it has provided dummy data earlier.
  * Protection proxy:
    * If an application does not have access to some resource then such proxies will talk to the objects in applications that have access to that resource and then get the result back.
  * Smart Proxy:
    * A smart proxy provides additional layer of security by interposing specific actions when the object is accessed. An example can be to check if the real object is locked before it is accessed to ensure that no other object can change it.

### Proxy Advantages

* Proxy pattern can be used to add security
* Proxy pattern can be used to increase performance

### Proxy Exmaples in JDK

* `java.rmi.*`
