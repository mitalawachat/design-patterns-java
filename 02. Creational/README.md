# Creational

## Builder

* Builder is a creational design pattern that lets you construct complex objects step by step.
* The pattern allows you to produce different types and representations of an object using the same construction code.

### Builder Advantages

* Parameters in contructor are minimized, thus flexible and readable code. No need to pass `null` for optional parameters.

### Builder Exmaples in JDK

* `java.lang.StringBuilder` (unsynchronized)
* `java.lang.StringBuffer` (synchronized)

----

## Factory

* Factory design pattern is used when we have a parent-class with multiple sub-classes, and based on input we need to return object of one of the sub-class.
* Factory is a component whose responsibility solely is for the wholesome (not piecewise) creation of objects.
* A factory can be external or reside inside the object as an inner class.
* Factory class can be `singleton`, or we can keep the `static` method that returns the subclass.

### Factory Advantages

* Object creation logic is abstracted in factory, thus making code less coupled and easy to extend.
* Factory design pattern provides approach to code for interface rather than implementation.

### Factory Exmaples in JDK

* `java.util.Calendar`
* `NumberFormatter`
* `valueOf()` method in wrapper classes like `Boolean`, `Integer` etc.

----

## Abstract Factory

* Abstract Factory pattern is almost similar to Factory Pattern except the fact that its more like factory of factories.

### Abstract Factory Advantages

* Abstract Factory pattern is 'factory of factories', thus can be easily extended to accomodate new factories and sub-classes
* Abstract Factory pattern avoid conditional logic of Factory pattern.

### Abstract Factory Exmaples in JDK

* `javax.xml.parsers.DocumentBuilderFactory`
* `javax.xml.transform.TransformerFactory`
* `javax.xml.xpath.XPathFactory`

----

## Prototype

* Prototype is an existing (partially or fully constructed) design.
* Prototype pattern provides a mechanism to copy the original object to a new object and then modify it according to our needs.

### Prototype Advantages

* It hides complexities of creating objects.
* The client application need not be unaware of object creation and representation.

----

## Singleton

* Singleton pattern restricts the instantiation of a class and ensures that only one instance of the class exists in the java virtual machine.
* The singleton class must provide a global access point to get the instance of the class.
* Singleton design pattern is also used in other design patterns like Abstract Factory, Builder, Prototype, Facade etc.

### Singleton Common Concepts

* `Private constructor` to restrict instantiation of the class from other classes.
* `Private static variable` of the same class that is the only instance of the class.
* `Public static method` that returns the instance of the class, this is the global access point for outer world to get the instance of the singleton class.

----
