package com.yearup.dealership;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class HomeScreen
{
    private static final Set<String> VALID_SIZES  = Set.of("8", "12", "16");
    private static final Set<String> VALID_CRUSTS = Set.of("thin", "regular", "thick", "cauliflower");
    private static final Set<String> VALID_DRINK_SIZES = Set.of("small", "medium", "large");

    private Scanner scanner;

    public HomeScreen()
    {
        scanner = new Scanner(System.in);
    }

    public void run()
    {
        boolean running = true;

        while (running)
        {
            System.out.println("\n=== PIZZA-licious ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice)
            {
                case 1:
                    createOrder();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter 0 or 1.");
            }
        }
    }

    private void createOrder()
    {
        Order order = new Order();
        CheckoutScreen checkoutScreen = new CheckoutScreen();
        boolean ordering = true;

        while (ordering)
        {
            System.out.println("\n=== ORDER SCREEN ===");
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Side");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice)
            {
                case 1:
                    order.addPizza(createPizza());
                    break;
                case 2:
                    order.addDrink(createDrink());
                    break;
                case 3:
                    order.addSide(createSide());
                    break;
                case 4:
                    if (checkoutScreen.startCheckout(order))
                    {
                        ordering = false;
                    }
                    break;
                case 0:
                    System.out.println("Order canceled.");
                    ordering = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private Pizza createPizza()
    {
        System.out.println("\n=== CREATE PIZZA ===");

        // --- Size ---
        String size = "";
        while (!VALID_SIZES.contains(size))
        {
            System.out.print("Choose size (8, 12, 16): ");
            size = scanner.nextLine().trim();
            if (!VALID_SIZES.contains(size))
            {
                System.out.println("Invalid size. Please enter 8, 12, or 16.");
            }
        }

        // --- Crust ---
        String crust = "";
        while (!VALID_CRUSTS.contains(crust.toLowerCase()))
        {
            System.out.print("Choose crust (thin, regular, thick, cauliflower): ");
            crust = scanner.nextLine().trim();
            if (!VALID_CRUSTS.contains(crust.toLowerCase()))
            {
                System.out.println("Invalid crust. Choose: thin, regular, thick, or cauliflower.");
            }
        }

        Pizza pizza = new Pizza(size, crust);

        // --- Stuffed crust ---
        System.out.print("Stuffed crust? (yes/no): ");
        String stuffed = scanner.nextLine().trim();
        pizza.setStuffedCrust(stuffed.equalsIgnoreCase("yes"));

        // --- Meat ---
        System.out.print("Add meat (leave blank if none): ");
        String meat = scanner.nextLine().trim();
        if (!meat.isEmpty()) pizza.addMeat(meat);

        // --- Cheese ---
        System.out.print("Add cheese (leave blank if none): ");
        String cheese = scanner.nextLine().trim();
        if (!cheese.isEmpty()) pizza.addCheese(cheese);

        // --- Toppings ---
        int toppingQuantity = 0;
        while (toppingQuantity < 0)
        {
            // guard skipped — handled below
        }
        System.out.print("Topping quantity: ");
        toppingQuantity = readNonNegativeInt("Topping quantity must be 0 or more: ");
        for (int i = 0; i < toppingQuantity; i++)
        {
            System.out.print("Add topping #" + (i + 1) + " (leave blank to skip): ");
            String topping = scanner.nextLine().trim();
            if (!topping.isEmpty()) pizza.addTopping(topping);
        }

        // --- Sauce ---
        System.out.print("Add sauce (leave blank if none): ");
        String sauce = scanner.nextLine().trim();
        if (!sauce.isEmpty()) pizza.addSauce(sauce);

        return pizza;
    }

    private Drink createDrink()
    {
        System.out.println("\n=== CREATE DRINK ===");

        System.out.print("Drink name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Drink";

        String size = "";
        while (!VALID_DRINK_SIZES.contains(size.toLowerCase()))
        {
            System.out.print("Drink size (small, medium, large): ");
            size = scanner.nextLine().trim();
            if (!VALID_DRINK_SIZES.contains(size.toLowerCase()))
            {
                System.out.println("Invalid size. Choose: small, medium, or large.");
            }
        }

        return new Drink(name, size);
    }

    private Side createSide()
    {
        System.out.println("\n=== CREATE SIDE ===");

        System.out.print("Side name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Side";

        return new Side(name);
    }

    // ── Input helpers ──────────────────────────────────────────────────────────

    /** Reads an integer; loops on bad input instead of crashing. */
    private int readInt()
    {
        while (true)
        {
            try
            {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            catch (InputMismatchException e)
            {
                scanner.nextLine();
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    /** Reads a non-negative integer, re-prompting with a custom message on failure. */
    private int readNonNegativeInt(String retryMessage)
    {
        while (true)
        {
            try
            {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= 0) return value;
                System.out.print(retryMessage);
            }
            catch (InputMismatchException e)
            {
                scanner.nextLine();
                System.out.print(retryMessage);
            }
        }
    }
}