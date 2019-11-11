# SOLID Design Principles

## Introduction

* Introduced by Robert C. Martin

## Single Responsibility Principle (SRP)

* Software entities (classes, modules, functions, etc.) should have single responsibility, and thus only a single reason to change
* _Separation Of Concern_ - different classes handling different/independent tasks/problems
* Keeping a class focused on a single concern makes the class more robust.
* The term was introduced by Robert C. Martin in an article by the same name as part of his Principles of Object Oriented Design

## Open/Close Principle (OCP)

* Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification; that is, such an entity can allow its behaviour to be extended without modifying its source code.
* Bertrand Meyer is generally credited for having originated the term open/closed principle, which appeared in his 1988 book Object Oriented Software Construction.

## Liskov Substitution Principle (LSP)

* Objects should be able to be replaced by their subtypes without breaking the application. That requires the objects of your subclasses to behave in the same way as the objects of your superclass.
* It was initially introduced by Barbara Liskov in a 1987 conference keynote address titled Data abstraction and hierarchy
* You can achieve that by following a few rules:
  * An overridden method of a subclass needs to accept the same input parameter values as the method of the superclass. You can implement less restrictive validation rules, but you are not allowed to enforce stricter ones in your subclass.
  * The return value of a method of the subclass needs to comply with the same rules as the return value of the method of the superclass. You can only decide to apply even stricter rules by returning a specific subclass of the defined return value, or by returning a subset of the valid return values of the superclass.

## Inteface Segregation Principle (ISP)

* Interfaces sould be client specific rather than general
* Don't put too mush into interface; split into separate interface. Such shrunken interfaces are also called _role interfaces_.
* The ISP was first used and formulated by Robert C. Martin while consulting for Xerox.

## Dependency Inversion Principle (DIP)

* Robert C. Martin’s definition of the Dependency Inversion Principle consists of two parts:
  * High-level modules should not depend on low-level modules. Both should depend on abstractions (e.g. interfaces).
  * Abstractions should not depend on details. Details (concrete implementations) should depend on abstractions.
* DIP is about inverting the classic dependency between high-level and low-level components by abstracting away the interaction between them.
