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
Step-by-Step Customization: Build fully custom pizzas with size, crust, topping, and sauce options.
Topping Quantity Multipliers: Prompts users for specific topping counts and loops input prompts exactly matching customer demands.
Flexible Cart: Add multiple items (pizzas, drinks, and sides) to the same order.
Order Review: View a complete order breakdown before committing to checkout.
🌟 Signature Pizzas
Pre-configured Presets: Quick selection for classic options such as a 12" Margherita or an 8" Veggie pizza.
Dynamic Customization: Extends the base pizza logic so users can still add extra items after choosing a preset.
💰 Pricing System
Size-Based Scaling: Pizza base costs adjust automatically across 8", 12", and 16" options.
Tiered Topping Rates: Premium ingredients (meats and cheeses) calculate extra fees based on the size tier.
Standard Products: Drinks use size-based pricing, and sides use flat pricing ($1.50).
🛡️ Validation & Checkout Rules
Input Fail-Safes: Clears input scanner buffers to prevent accidental skips or application crashes on incorrect entries.
0-Pizza Validation Constraints: Enforces specific business rules. If an order lacks pizzas, it blocks checkout unless a drink or a garlic knots side is present.
🧾 Receipt System
Buffered I/O Serialization: Automatically generates a formatted text receipt using BufferedWriter and FileWriter.
Unique Timestamp Files: Saves records to the receipts/ directory using unique yyyyMMdd-HHmmss.txt names to avoid overwriting files.
🛠️ Tech Stack
Language Runtime: Java SE 8 or higher
Console I/O: java.util.Scanner
Data Collections: java.util.ArrayList
File Output Streams: java.io.FileWriter, java.io.BufferedWriter
System Utilities: java.time.LocalDateTime, java.time.format.DateTimeFormatter
Development Environment: IntelliJ IDEA, Git, GitHub
📐 Architecture & Design
Class Responsibilities
Main: Serves as the program entry bootstrap point.
HomeScreen: Manages interactive menus, scanner input, and loop structures.
CheckoutScreen: Displays the final summary, validates business logic constraints, and confirms purchase requests.
MenuItem: Abstract parent class defining the universal framework and pricing contract for all store inventory assets.
Order: Aggregates all selections in a single polymorphic ArrayList<MenuItem> and computes the total price.
Pizza / Drink / Side: Concrete sub-classes that define specific pricing matrix calculations.
MargheritaPizza / VeggiePizza: Signature pizza types utilizing inheritance to assign ingredient properties.
ReceiptManager: Writes order breakdowns out to disk files.
📐 Architecture & Data Flow

The sequence diagram below displays the program execution flow from initialization up to successful file saving:

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

Make sure a Java Development Kit (JDK 8 or higher) is installed.

Steps
git clone <your-repo-url>
cd pizza-licious
mkdir receipts
javac src/com/yearup/dealership/*.java -d out
java -cp out com.yearup.dealership.Main
