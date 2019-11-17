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
