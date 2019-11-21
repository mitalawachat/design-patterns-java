# Behavioral

## Chain Of Responsility

* Chain Of Responsility is sequence of handlers processing an event one after another.
* Types:
  * Method Chaining:
    * One method calls the entine chain of methods
  * Command Query Separation:
    * `Command Query Separation`: Having Separate means of sending `Commands` and `Queries`.
    * `Command`: Asking for an action or change
    * `Query`: Asking for information

### Chain Of Responsility Advantages

* You can control the order of request handling.

### Chain Of Responsility Exmaples in JDK

* `java.util.logging.Logger#log()`
* `javax.servlet.Filter#doFilter()`

----

## Command

* Command is an object which represents an instruction to perform a particular action. It contains all the information necessary to perform action.
* Command pattern allows us to decouple objects that produce the commands from their consumers.

### Command Advantages

* Command pattern makes code extensible as we can add new commands without changing existing code.
* Reduces coupling the invoker and receiver of a command.
* Can be used to track history
* Can be used to allow undo operations

### Command Exmaples in JDK

* `java.lang.Runnable`
* `javax.swing.Action`

----

## Interpreter

* Interpreter pattern is used to defines a grammatical representation for a language and provides an interpreter to deal with this grammar.
* Interpreter is a component that processes structured text data.
* Stages:
  * Lexing: Turning text into separate lexical tokens.
  * Parsing: Interpreting sequence of tokens.

### Interpreter Exmaples in JDK

* `java.util.Pattern`
* `java.text.Format`

----

----

## Iterator

* Iteration (traversal) is a core functionality of various data structures.
* An iterator is a class that facilitates the traversal
  * Keeps a reference of the current element
  * Knows how to move to a different element

### Iterator Exmaples in JDK

* `java.util.List`
* `java.util.Scanner`

----

## Mediator

* Mediator facilitates communication between other components without them necessarily being aware of each other or having direct (reference) access to each other.

### Mediator Advantages

* Allows loose coupling by encapsulating the way disparate sets of objects interact and communicate with each other.
* Allows for the actions of each object set to vary independently of one another.

----

## Memento

* Memento is a token/handle representing the system state.
* Memento let's us rollback to the state when the token was generated.
* Memento may or may not directly expose state information.

----

## Null Object

* Implement the required interface.
* Rewrite the methods with empty bodies.
  * If method is non-void return default value for a given type.
  * If these values are ever used then it can cause issue.
* Supply the instance of Null Object in place of actual object.
* Hope everything goes OK!

----

## Observer

* Observer is an object that wishes to be informed about events happening in the system. The entity generating the events is an observable.
* Observer pattern is used when we need to be informed when certain things happen.
  * Object's field changes.
  * Object does something.
  * Some external event occurs.
* We want to listen to events and notified when they occur.

----
