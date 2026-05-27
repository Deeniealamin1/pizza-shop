# PIZZA-licious

### A terminal-based pizza ordering system built in Java that allows users to create custom pizzas, manage orders, and generate timestamped receipts.

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](#)
[![Java Version](https://img.shields.io/badge/java-8%2B-blue.svg)](#)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](#)

---

## 🗺️ System Overview

PIZZA-licious is a command-line application designed to simulate a pizza ordering system. Users can build custom pizzas, add drinks and sides, review their order, and complete checkout with an automatically generated receipt saved locally.

The project demonstrates core object-oriented programming principles including inheritance, polymorphism, encapsulation, and abstraction.

### Core Components

- **Main:** Entry point of the application  
- **HomeScreen:** Handles all user input and menu navigation  
- **Order:** Stores all items in the current order and calculates totals  
- **MenuItem (abstract):** Base class for all products  
- **Pizza / Drink / Side:** Concrete implementations with pricing logic  
- **SignaturePizza:** Pre-built pizza templates using inheritance  
- **CheckoutScreen:** Handles order review and confirmation  
- **ReceiptManager:** Writes completed orders to a file in `/receipts`  

---

## ⚡ Features

### 🍕 Order System
- Build custom pizzas with size, crust, toppings, and sauces
- Add drinks with size-based pricing
- Add sides with fixed pricing
- Multiple items per order supported

### 🌟 Signature Pizzas
- Pre-configured pizzas (e.g., Margherita, Veggie)
- Optional customization after selection

### 💰 Pricing System
- Size-based pizza pricing (8", 12", 16")
- Premium toppings priced per size
- Drinks priced by size
- Automatic total calculation using polymorphism

### 🧾 Receipt System
- Automatic receipt generation after checkout
- Saved to `/receipts` folder
- Timestamped filenames to prevent overwrites

### 🛡️ Input Validation
- Prevents invalid menu selections
- Handles incorrect input safely
- Ensures valid pizza configuration inputs

---

## 🛠️ Tech Stack

- Java (JDK 8+)
- Object-Oriented Programming (OOP)
- ArrayList collections
- Scanner for input handling
- File I/O (`java.io`, `java.nio.file`)
- LocalDateTime for timestamps

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
