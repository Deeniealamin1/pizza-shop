Ah, good catch! GitHub's Markdown parser is throwing an error because the text headings like 📂 Project Structure got mixed into the bottom of the Mermaid block before it was closed out with the triple backticks (```).

Here is the exact revised README.md block with the sequence diagram cleanly closed out so that GitHub dark mode and the rich display render beautifully without any parse errors:

Markdown
# PIZZA-licious

### A terminal-based pizza ordering system built in Java with customizable orders, receipt generation, and structured checkout flow.

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](#)
[![Java Version](https://img.shields.io/badge/java-8%2B-blue.svg)](#)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](#)

---

## 🗺️ System Overview

### Project Description
PIZZA-licious is a Java-based command-line ordering system for a pizza shop. It allows users to build custom pizzas, select signature pizzas, add drinks and sides, and complete checkout with a generated receipt file. 

The system replaces manual order tracking with a structured digital workflow that ensures accurate pricing and consistent order handling.

### Problem Solved & Target User
This application replaces a manual paper-based ordering process that was prone to calculation mistakes and missing records. It ensures:
* Accurate order totals
* Organized order tracking
* Saved receipts for every transaction

The target user is a cashier taking customer orders at a pizza shop terminal.

---

## ⚡ Features

### 🍕 Order Management
* **Step-by-Step Customization:** Build fully custom pizzas with size, crust, topping, and sauce options.
* **Topping Quantity Multipliers:** Prompts users for specific topping counts and loops input prompts exactly matching customer demands.
* **Flexible Cart:** Add multiple items (pizzas, drinks, and sides) to the same order.
* **Order Review:** View a complete order breakdown before committing to checkout.

### 🌟 Signature Pizzas
* **Pre-configured Presets:** Quick selection for classic options such as a 12" Margherita or an 8" Veggie pizza.
* **Dynamic Customization:** Extends the base pizza logic so users can still add extra items after choosing a preset.

### 💰 Pricing System
* **Size-Based Scaling:** Pizza base costs adjust automatically across 8", 12", and 16" options.
* **Tiered Topping Rates:** Premium ingredients (meats and cheeses) calculate extra fees based on the size tier.
* **Standard Products:** Drinks use size-based pricing, and sides use flat pricing ($1.50).

### 🛡️ Validation & Checkout Rules
* **Input Fail-Safes:** Clears input scanner buffers to prevent accidental skips or application crashes on incorrect entries.
* **0-Pizza Validation Constraints:** Enforces specific business rules. If an order lacks pizzas, it blocks checkout unless a drink or a garlic knots side is present.

### 🧾 Receipt System
* **Buffered I/O Serialization:** Automatically generates a formatted text receipt using `BufferedWriter` and `FileWriter`.
* **Unique Timestamp Files:** Saves records to the `receipts/` directory using unique `yyyyMMdd-HHmmss.txt` names to avoid overwriting files.

---

## 🛠️ Tech Stack

* **Language Runtime:** Java SE 8 or higher
* **Console I/O:** `java.util.Scanner`
* **Data Collections:** `java.util.ArrayList`
* **File Output Streams:** `java.io.FileWriter`, `java.io.BufferedWriter`
* **System Utilities:** `java.time.LocalDateTime`, `java.time.format.DateTimeFormatter`
* **Development Environment:** IntelliJ IDEA, Git, GitHub

---

## 📐 Architecture & Design

### Class Responsibilities
* **`Main`:** Serves as the program entry bootstrap point.
* **`HomeScreen`:** Manages interactive menus, scanner input, and loop structures.
* **`CheckoutScreen`:** Displays the final summary, validates business logic constraints, and confirms purchase requests.
* **`MenuItem`:** Abstract parent class defining the universal framework and pricing contract for all store inventory assets.
* **`Order`:** Aggregates all selections in a single polymorphic `ArrayList<MenuItem>` and computes the total price.
* **`Pizza` / `Drink` / `Side`:** Concrete sub-classes that define specific pricing matrix calculations.
* **`MargheritaPizza` / `VeggiePizza`:** Signature pizza types utilizing inheritance to assign ingredient properties.
* **`ReceiptManager`:** Writes order breakdowns out to disk files.

### Data Flow Diagram
The sequence diagram below displays the program execution flow from initialization up to successful file saving:

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
    loop Order Creation
        User->>HS: Start New Order
        HS->>O: Create Order
        User->>HS: Add Pizza / Drink / Side
        HS->>O: addItem()
    end

    User->>HS: Checkout
    HS->>CS: startCheckout(order)

    alt Order Fails 0-Pizza Validation Rule
        CS-->>HS: Block checkout & return false
    else Order is valid
        CS->>O: displayOrder() + getTotal()
        User->>CS: Confirm purchase
        CS->>RM: saveReceipt(order)
        RM->>RM: Write via BufferedWriter to receipts/
        CS-->>HS: Finish order & return true
    end
📂 Project Structure
Plaintext
PIZZA-licious/
│
├── src/
│   └── com/yearup/dealership/
│       ├── Main.java             # System boots execution engine here
│       ├── HomeScreen.java       # Coordinates console prompts and quantity loops
│       ├── CheckoutScreen.java   # Evaluates cart rules and verifies user confirmation
│       ├── MenuItem.java         # Abstract base component defining shared structure
│       ├── Order.java            # Unified model container tracking polymorph collections
│       ├── Pizza.java            # Handles size-relative calculations
│       ├── MargheritaPizza.java  # Extends Pizza with preconfigured ingredients
│       ├── VeggiePizza.java      # Extends Pizza with preconfigured vegetables
│       ├── Drink.java            # Standard item tracking variable drink sizes
│       ├── Side.java             # Product tracking side items with uniform pricing models
│       └── ReceiptManager.java   # Output utility writing log details to system disk
│
├── receipts/                     # Destination folder for text receipt log generation
└── README.md                     # Application architectural documentation
🚀 How to Run
Follow these command terminal instructions to compile and run the point-of-sale application on your machine:

Prerequisites
Make sure a Java Development Kit (JDK 8 or higher) is installed. Check using this command:

Bash
java -version
Steps to Execute
Clone the Repository:

Bash
git clone <your-repo-url>
cd pizza-licious
Create the Required Local Storage Folders:
Note: This storage structure must exist prior to processing orders, as file writers will not dynamically create missing directories.

Bash
mkdir receipts
Compile the Class Source Files:

Bash
javac src/com/yearup/dealership/*.java -d out
Launch the Core Application Engine:

Bash
java -cp out com.yearup.dealership.Main
🧠 Object-Oriented Programming Concepts
The architecture utilizes the four foundational pillars of Object-Oriented Programming (OOP):

1. Encapsulation
Data fields inside our objects are protected using the private access modifier (e.g., fields tracking choices inside Pizza.java). Classes control mutations cleanly through explicit helper methods like .addTopping() instead of allowing outside components to modify internal collections directly.

2. Inheritance
Shared behaviors are defined once inside a parent class to eliminate redundant properties. Pizza, Drink, and Side extend the abstract base class MenuItem to reuse core properties like names. Signature pizzas like MargheritaPizza extend Pizza to instantly inherit all size and crust properties while auto-loading pre-defined toppings inside their constructors.

3. Polymorphism
The application manages all products universally under their parent data classification. Inside Order.java, items are tracked inside a single list:

Java
private ArrayList<MenuItem> items;
When calculating financial metrics, the application loops through this list and calls .calculatePrice(). Java dynamically determines at runtime whether to call the size-scaled method inside Pizza, the size-lookup rules inside Drink, or the flat fee calculation inside Side.

4. Abstraction
We implement abstraction through our template parent structure:

Java
public abstract class MenuItem
A generic menu item cannot exist by itself or have a standalone price, so we mark MenuItem as abstract. It acts as an operational contract by defining public abstract double calculatePrice();, forcing each concrete child class to provide its own accurate pricing logic.

📋 Example Console & Written Receipt Output
Terminal Checkout Display Look
Plaintext
=== CHECKOUT SCREEN ===

=== ORDER DETAILS ===

Pizza Details:
- Size: 16"
- Crust: thick
- Stuffed Crust: Yes
- Cheeses: [cheddarrrr]
- Toppings: [jalaapeno, cooki, bpnn]
- Sauces: [marinara]
  Item Price: $20.75
large cola - $3.00
mac n cheese - $1.50

Total: $25.25

1) Confirm and Place Order
0) Cancel and Go Back to Menu
Choose an option: 1
Receipt successfully saved to: receipts/20260527-124515.txt
Thank you! Order processed successfully.
Resulting Output File (receipts/20260527-124515.txt)
Plaintext
PIZZA-licious Receipt
=====================

Pizza
-----------------
Price: $20.75

- large cola - $3.00
- mac n cheese - $1.50
=====================
Total: $25.25
