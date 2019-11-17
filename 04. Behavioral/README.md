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
