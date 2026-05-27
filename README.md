PIZZA-licious
A terminal-based pizza ordering system built in Java with customizable orders, receipt generation, and structured checkout flow.






🗺️ System Overview
Project Description

PIZZA-licious is a Java-based command-line ordering system for a pizza shop. It allows users to build custom pizzas, select signature pizzas, add drinks and sides, and complete checkout with a generated receipt file.

The system replaces manual order tracking with a structured digital workflow that ensures accurate pricing and consistent order handling.

Problem Solved & Target User

This application replaces a manual paper-based ordering process that was prone to calculation mistakes and missing records. It ensures:

Accurate order totals
Organized order tracking
Saved receipts for every transaction

The target user is a cashier taking customer orders at a pizza shop terminal.

⚡ Features
🍕 Order Management
Step-by-step custom pizza creation (size, crust, toppings, sauce)
Add multiple items (pizzas, drinks, sides) to one order
Full order review before checkout
🌟 Signature Pizzas
Pre-configured options like Margherita and Veggie
Built using inheritance for reusable base logic
Still allows customization after selection
💰 Pricing System
Size-based pizza pricing (8", 12", 16")
Extra charges for premium toppings
Drinks use tiered pricing
Sides use flat pricing ($1.50)
🛡️ Validation & Rules
Input validation prevents crashes from invalid entries
Checkout rules ensure valid order state
Prevents checkout when order does not meet requirements
🧾 Receipt System
Uses BufferedWriter and FileWriter for file output
Generates receipt for every completed order
Timestamped filenames prevent overwriting
🛠️ Tech Stack
Java SE 8+
java.util.Scanner
java.util.ArrayList
java.io.FileWriter
java.io.BufferedWriter
java.time.LocalDateTime
IntelliJ IDEA, Git, GitHub
📐 Architecture & Design
Class Responsibilities
Main: Entry point of the application
HomeScreen: Handles user input and menus
CheckoutScreen: Validates and finalizes orders
MenuItem: Abstract base class for all products
Order: Stores items and calculates total
Pizza / Drink / Side: Concrete product logic
MargheritaPizza / VeggiePizza: Pre-built signature pizzas
ReceiptManager: Writes order data to file
📐 Architecture & Data Flow

The diagram below shows how the system processes an order.

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

    alt Order Fails Validation Rule
        CS-->>HS: Block checkout
    else Order is valid
        CS->>O: displayOrder() + getTotal()
        User->>CS: Confirm purchase
        CS->>RM: saveReceipt(order)
        RM->>RM: Write file to receipts/
        CS-->>HS: Order complete
    end
📂 Project Structure
PIZZA-licious/
│
├── src/
│   └── com/yearup/dealership/
│       ├── Main.java
│       ├── HomeScreen.java
│       ├── CheckoutScreen.java
│       ├── MenuItem.java
│       ├── Order.java
│       ├── Pizza.java
│       ├── MargheritaPizza.java
│       ├── VeggiePizza.java
│       ├── Drink.java
│       ├── Side.java
│       └── ReceiptManager.java
│
├── receipts/
└── README.md
🚀 How to Run
Prerequisites
Java JDK 8+
Steps
git clone <your-repo-url>
cd pizza-licious
mkdir receipts
javac src/com/yearup/dealership/*.java -d out
java -cp out com.yearup.dealership.Main
🧠 OOP Concepts Used
Encapsulation: Private fields protect internal state
Inheritance: Signature pizzas reuse base Pizza class
Polymorphism: All items stored as MenuItem
Abstraction: MenuItem defines shared contract for pricing

If you want next step, I can:

make it look like a top-tier FAANG-style README
or simplify it further to look like a real internship GitHub submission
or add a clean UML diagram instead of Mermaid (safer for GitHub rendering)
