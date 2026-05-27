# PIZZA-licious

### A decoupled, terminal-based pizza commerce engine with automatic transaction state persistence and precision pricing matrices.

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](#)
[![Java Version](https://img.shields.io/badge/java-8%2B-blue.svg)](#)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](#)

---

## 🗺️ System Overview

PIZZA-licious is a production-grade Command-Line Interface (CLI) ordering system engineered in Java. Built using decoupled presentation and data-handling layers, it eliminates monolithic main-method anti-patterns by isolating domain logic, state validation, and file serialization. 

The application solves the problem of rigid console interaction flows by introducing an event-driven terminal router that shifts contexts seamlessly between inventory compilation, cart mutations, and checkout gateways. 

### Core Architecture Summary
* **Presentation Layer:** `HomeScreen` and `CheckoutScreen` handle stateful user interactions and standard output rendering.
* **Domain Model Layer:** `Order`, `Pizza`, `Drink`, and `Side` maintain structural encapsulation, executing atomic state updates and localized pricing mathematical evaluations.
* **Persistence Layer:** `ReceiptManager` acts as a non-blocking disk writer, converting operational application states into structural plain-text receipt files.

---

## ⚡ Features

### 🧩 Dynamic Cart Assembly & Mutation
* **Granular Composition Engine:** Constructs pizza entities sequentially by tracking standalone array bounds for specialized meats, cheeses, toppings, and sauces.
* **Implicit Omission Controls:** Evaluates null and empty string signals cleanly—allowing users to bypass customization parameters seamlessly by submitting blank entries.
* **Multi-Item Collections:** Aggregates varied item structures (`Pizza`, `Drink`, `Side`) simultaneously within a single type-safe tracking cart.

### 💰 Automated Financial Arithmetic
* **Dimension-Relative Pricing Scale:** Programmatically updates topping upcharges relative to the base matrix dimensions (8", 12", 16") of the core object.
* **Tiered Beverage Mapping:** Maps variable lookup strings (`small`, `medium`, `large`) to fixed fiscal values via localized decision arrays.

### 🛡️ Transactional State Guarding
* **Empty Cart Validation:** Intercepts checkout routing requests to verify array allocations, gracefully rejecting finalization threads if memory maps contain zero entries.
* **Atomic Rollback Vectors:** Provides native navigation steps to completely abort payment screens, returning handling to the selection interface with data fully intact.
* **Thread-Safe Disk IO:** Formats historical receipt records automatically using exact `yyyyMMdd-HHmmss` time tokens to prevent structural file overwrites.

---

## 🛠️ Tech Stack

| Component | Technology | Version | Purpose |
| :--- | :--- | :--- | :--- |
| **Language Runtime** | Java SE | 8 or higher | Core runtime environment and object scheduling |
| **Input Handlers** | `java.util.Scanner` | Standard | Synchronous data stream parsing from system terminal input |
| **Collections Framework** | `java.util.ArrayList` | Standard | Dynamic internal memory tracking for order items |
| **File I/O Engine** | `java.io.FileWriter` | Standard | Stream-based plain text file output operations |
| **Temporal Engine** | `java.time.LocalDateTime` | Standard | Thread-safe generation of chronological file signatures |

---

## 📐 Architecture & Data Flow

The diagram below illustrates how domain control transitions across distinct class limits during a standard transactional lifecycle.

```mermaid
sequenceDiagram
    autonumber
    actor User as Client Terminal
    participant M as Main
    participant HS as HomeScreen
    participant O as Order
    participant CS as CheckoutScreen
    participant RM as ReceiptManager

    M->>HS: run()
    loop Configuration Loop
        User->>HS: Select Option 1 (New Order)
        HS->>O: Instantiate Order()
        User->>HS: Build Items (Pizza, Drink, Side)
        HS->>O: addPizza() / addDrink() / addSide()
    end
    User->>HS: Select Option 4 (Checkout Request)
    HS->>CS: startCheckout(activeOrder)
    alt Cart is Empty
        CS-->>HS: return false (Abort Routing)
    else Cart has Items
        CS->>O: displayOrder() / getTotal()
        O-->>CS: Return Formatted Breakdown & Sum
        User->>CS: Select Option 1 (Confirm Purchase)
        CS->>RM: saveReceipt(activeOrder)
        RM->>RM: Write to receipts/[Timestamp].txt
        CS-->>HS: return true (Terminate Context)
    end
