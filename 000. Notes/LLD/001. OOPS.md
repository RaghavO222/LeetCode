# Comprehensive Object-Oriented Programming (OOP) Guide

Welcome to this comprehensive guide on Object-Oriented Programming, focused specifically on Java. This repository serves as a deep dive into core OOP pillars, common interview pitfalls, and architectural best practices for modern software development.

## Table of Contents
1. [Classes, Inheritance, and Constructors](#1-classes-inheritance-and-constructors)
2. [Polymorphism & Method Dispatch](#2-polymorphism--method-dispatch)
3. [Abstraction: Abstract Classes vs. Interfaces](#3-abstraction-abstract-classes-vs-interfaces)
4. [Modern Java Interface Concepts (Java 8+)](#4-modern-java-interface-concepts-java-8)
5. [Architecture: Composition vs. Inheritance](#5-architecture-composition-vs-inheritance)
6. [State Management: Immutability](#6-state-management-immutability)

---

## 1. Classes, Inheritance, and Constructors
Inheritance represents an **"Is-A"** relationship, allowing subclasses to inherit fields and methods from parent classes, promoting code reuse.

### Key Concepts:
*   **`super()` and `this()`**: `super()` is used to call the parent class's constructor, while `this()` references the current instance. Constructor chaining is essential for proper object initialization.
*   **Access Modifiers**: 
    *   `private`: Accessible only within the same class.
    *   `protected`: Accessible within the same package and by subclasses.
    *   `public`: Accessible from anywhere.
---

## 1.5 Encapsulation (Data Hiding)

Encapsulation is the mechanism of bundling the data (variables) and the code acting on the data (methods) together as a single unit. More importantly, it restricts direct access to some of an object's components, which is a defensive programming technique called **data hiding**.

### Key Concepts:
*   **Access Modifiers:** The tools used to implement encapsulation.
    *   `private`: The strictest level. The field or method is only accessible within its own class.
    *   `protected`: Accessible within the same package and by subclasses.
    *   `public`: Accessible from anywhere.
*   **Getters and Setters:** Instead of making fields public, you make them `private` and provide `public` methods to read (getter) or modify (setter) the values. This allows the class to control *how* the data is modified (e.g., adding validation logic inside a setter).

### Example: The Vehicle Hierarchy
```java
class Vehicle {
    protected String brand;
    private int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void steer() {
        System.out.println(brand + " vehicle is steering...");
    }

    public int getYear() {
        return year;
    }
}

class Car extends Vehicle {
    protected String model;

    public Car(String brand, int year, String model) {
        super(brand, year);
        this.model = model;
    }

    @Override
    public void steer() {
        super.steer();
        System.out.println(model + " car is ready");
    }
    
    public void fuelUp() {
        System.out.println(model + " is fueling up");
    }
}

class ElectricCar extends Car {
    private int batteryLevel;

    public ElectricCar(String brand, int year, String model, int batteryLevel) {
        super(brand, year, model);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void fuelUp() {
        System.out.println(model + " is charging, battery at " + batteryLevel);
    }
}
```

## 1.5 Encapsulation (Data Hiding)

Encapsulation is the mechanism of bundling the data (variables) and the code acting on the data (methods) together as a single unit. More importantly, it restricts direct access to some of an object's components, which is a defensive programming technique called **data hiding**.

### Key Concepts:
*   **Access Modifiers:** The tools used to implement encapsulation.
    *   `private`: The strictest level. The field or method is only accessible within its own class.
    *   `protected`: Accessible within the same package and by subclasses.
    *   `public`: Accessible from anywhere.
*   **Getters and Setters:** Instead of making fields public, you make them `private` and provide `public` methods to read (getter) or modify (setter) the values. This allows the class to control *how* the data is modified (e.g., adding validation logic inside a setter).

### Example from the Notes:
In the `Vehicle` class, the `year` is encapsulated:
```java
class Vehicle {
    protected String brand; 
    
    // The data is hidden from the outside world
    private int year; 

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // Controlled access via a getter method
    public int getYear() {
        return year;
    }
}

```

## 2. Polymorphism & Method Dispatch

Polymorphism allows objects of different classes to be treated as objects of a common superclass.

### Overloading vs. Overriding
*   **Method Overloading (Compile-Time Polymorphism):** Same method name, different parameters (type, number, or order) within the same class.
*   **Method Overriding (Run-Time Polymorphism):** Subclass provides a specific implementation for a method already defined in its parent class.
    *   *Rules:* Cannot override constructors. Cannot assign weaker access privileges (e.g., cannot override a `public` method and make it `protected`).

### Dynamic Method Dispatch (Crucial Interview Concept)
Java uses the **Object's actual class at runtime**, not the reference type, to decide which version of an overridden method to execute.

```java
Car myCar = new ElectricCar("Tesla", 2024, "Model S", 85);
myCar.fuelUp(); // Outputs: Model S is charging, battery at 85
```

### Static Method Hiding (The Trap)
Static methods are bound at **compile-time** based on the *reference type*, not the object type. They do **not** get overridden; they get hidden.

```java
class Parent {
    static void greet() { System.out.println("Hello from Parent"); }
}
class Child extends Parent {
    static void greet() { System.out.println("Hello from Child"); }
}

Parent obj = new Child();
obj.greet(); // Outputs: "Hello from Parent" (Based on reference type)
```

### Object Casting
To access subclass-specific methods when using a parent reference, you must downcast explicitly:
```java
Parent obj = new Child();
// obj.childOnlyMethod(); // Compile error
((Child) obj).childOnlyMethod(); // Works
```

---

## 3. Abstraction: Abstract Classes vs. Interfaces


## 1. Conceptual Notes: The "Why" and "What"

### Identity vs. Capability
* **Abstract Class:** Defines an **"is-a"** relationship. It acts as the foundational identity of an object (e.g., a `Goblin` *is an* `Enemy`).
* **Interface:** Defines a **"can-do"** relationship. It is a behavioral contract applied to an object, regardless of its core identity (e.g., a `Phone` and a `Flashlight` can both be `Chargeable`).

### State and Memory
* **Abstract Classes:** Can hold **state** (instance variables) and manage an object's memory over time (e.g., tracking current health). Because they have state, they also have **constructors** to initialize that data.
* **Interfaces:** Cannot hold state. Any variables placed inside an interface are automatically treated as global, unchanging constants (`public static final`). Because there is no state, there are **no constructors**.

### Default Methods in Interfaces
Modern languages allow interfaces to have "default methods" (methods with actual code inside them).
* **The Catch:** These default methods still cannot access or change instance variables, because interfaces still cannot hold state. They simply provide a fallback behavior that child classes can use or override.

---

## 2. Cheat Sheet: The Rules of Code

### The Keyword Mapping
* **Class to Class** ➔ `extends` (Inherits identity; limited to ONE)
* **Class to Interface** ➔ `implements` (Signs a contract; can be MULTIPLE)
* **Interface to Interface** ➔ `extends` (Stacks contracts; can be MULTIPLE)

### The "Pass the Buck" Rule (Empty Methods)
* **Concrete Classes:** The buck stops here. They **must** write the code for all empty methods inherited from abstract classes or interfaces.
* **Abstract Classes:** They act as middlemen. If an abstract class extends another abstract class or implements an interface, it **does not** have to write the code for the empty methods. It passes the responsibility down to its future concrete children.

### The Multiple Inheritance Rules
* A class can only extend **ONE** abstract class.
* A class can implement **MULTIPLE** interfaces.
* An interface can extend **MULTIPLE** interfaces.
* An interface **CANNOT** extend a class (because that would inherit state).

### How to Handle Collisions

**1. Method Collision (The Diamond Problem)**
If a class implements two interfaces that have the exact same default method, the compiler throws an error.
* **The Fix:** You must explicitly override the method in your class. 
```java
@Override
public void sharedMethod() {
    // Explicitly choose one parent's method to resolve ambiguity
    InterfaceName.super.sharedMethod(); 
}
```

**2. Variable Collision**
If a class implements two interfaces with the exact same constant variable, the compiler allows it until you try to use it.
* **The Fix:** You must explicitly state which one you want.
```java
// Instead of just printing 'MAX_POWER'
System.out.println(InterfaceName.MAX_POWER);
```

---

## 4. Modern Java Interface Concepts (Java 8+)

With modern Java, interfaces have evolved significantly, blurring the lines between them and abstract classes. This is highly relevant for enterprise-level development.

*   **Default Methods (Java 8):** Interfaces can now have methods with bodies using the `default` keyword. This allows adding new methods to interfaces without breaking existing implementations (crucial for maintaining large codebases).
    ```java
    public interface Drivable {
        void drive();
        default void turnOnLights() {
            System.out.println("Lights turned on.");
        }
    }
    ```
*   **Static Methods (Java 8):** Interfaces can contain static methods, providing utility functions related to the interface without needing a separate companion class (e.g., `Collections` vs `Collection`).
*   **Private Methods (Java 9):** Interfaces can have `private` and `private static` methods to share common logic between default methods, preventing code duplication within the interface itself.

---

## 5. Architecture: Composition vs. Inheritance

A fundamental design decision is choosing between Composition and Inheritance. 

*   **Inheritance ("Is-A"):** E.g., `ElectricCar` *is a* `Car`. 
    *   *Pros:* Easy to understand, built-in language feature.
    *   *Cons:* Tight coupling. Changing the parent class can inadvertently break subclasses (Fragile Base Class problem). Deep inheritance trees become impossible to maintain.
*   **Composition ("Has-A"):** E.g., `Car` *has an* `Engine`. Instead of inheriting, you instantiate objects of other classes as fields.
    *   *Pros:* Highly flexible. Allows changing behavior at runtime (Strategy Pattern). Loosely coupled and much easier to unit test via mocking.

**Best Practice:** *Favor Composition over Inheritance.* Use inheritance only when a true "Is-A" relationship exists and subclasses perfectly adhere to the parent's contract.

---

## 6. State Management: Immutability

An immutable object is an object whose internal state remains constant after it has been entirely created. This is a highly sought-after concept in experienced engineering roles.

### Why Immutability?
*   **Thread Safety:** Immutable objects are inherently thread-safe. Multiple threads can access them concurrently without synchronization issues, which is vital for high-performance backends.
*   **Predictability:** Reduces bugs caused by unintended side effects.

### How to Create an Immutable Class in Java:
1.  Declare the class as `final` so it cannot be extended.
2.  Make all fields `private` and `final`.
3.  Do not provide any setter methods.
4.  Initialize all fields via a constructor.
5.  **Defensive Copying:** If fields contain references to mutable objects (like arrays or `Date`), return a *copy* of the object in the getter, not the original reference.

```java
public final class ImmutableUser {
    private final String username;
    private final List<String> roles;

    public ImmutableUser(String username, List<String> roles) {
        this.username = username;
        // Defensive copy during creation
        this.roles = new ArrayList<>(roles); 
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        // Defensive copy on return to prevent modification
        return new ArrayList<>(roles); 
    }
}
```
*Note: In Java 14+, you can use `record` classes to achieve immutable data carriers with much less boilerplate.*
