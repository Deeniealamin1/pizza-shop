package com.pluralsight.ui;

import com.pluralsight.models.SignaturePizza;
import com.pluralsight.models.Drink;
import com.pluralsight.models.Order;
import com.pluralsight.models.Pizza;
import com.pluralsight.models.Side;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeScreen {
    private Scanner scanner;

    public HomeScreen() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Deenie's Pizzeria ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");

            int userChoice = getValidInt();

            switch (userChoice) {
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

    private void createOrder() {
        Order order = new Order();
        CheckoutScreen checkoutScreen = new CheckoutScreen();
        boolean ordering = true;

        while (ordering) {
            System.out.println("\n=== ORDER SCREEN ===");
            System.out.println("1) Add Custom Item");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Side (chips/garlic knots/etc.)");
            System.out.println("4) Add Signature Item");
            System.out.println("5) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choose an option: ");

            int userChoice = getValidInt();

            switch (userChoice) {
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
                    Pizza signaturePizza = createSignaturePizza();
                    if (signaturePizza != null) order.addPizza(signaturePizza);
                    break;
                case 5:
                    boolean finished = checkoutScreen.startCheckout(order);
                    if (finished) ordering = false;
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

    private Pizza createPizza() {
        System.out.println("\n=== ADD ITEM SCREEN ===");

        // Aligned to sequential expectations: type selection followed by size
        String crust = getValidString("Enter type selection (bread/crust/shell/custom type): ");
        String size = getValidSize();

        Pizza pizza = new Pizza(size, crust);

        System.out.print("Add premium meat (leave blank if none): ");
        String meat = scanner.nextLine().trim();
        if (!meat.isEmpty()) pizza.addMeat(meat);

        System.out.print("Add premium cheese (leave blank if none): ");
        String cheese = scanner.nextLine().trim();
        if (!cheese.isEmpty()) pizza.addCheese(cheese);

        System.out.print("How many regular toppings? ");
        int toppingCount = getValidNonNegativeInt();
        for (int i = 0; i < toppingCount; i++) {
            System.out.print("  Regular Topping #" + (i + 1) + " (leave blank to skip): ");
            String topping = scanner.nextLine().trim();
            if (!topping.isEmpty()) pizza.addRegularTopping(topping);
        }

        String specialOption = getValidSpecialOption();
        pizza.setSpecialOption(specialOption);

        return pizza;
    }

    private Pizza createSignaturePizza() {
        System.out.println("\n=== SIGNATURE ITEMS ===");
        System.out.println("1) Margherita Preset");
        System.out.println("2) Veggie Preset");
        System.out.println("0) Back");
        System.out.print("Choose an option: ");

        int userChoice = getValidInt();

        if (userChoice == 0) return null;

        SignaturePizza pizza;
        if (userChoice == 1) {
            pizza = SignaturePizza.margherita();
        } else if (userChoice == 2) {
            pizza = SignaturePizza.veggie();
        } else {
            System.out.println("Invalid option. Returning to order menu.");
            return null;
        }

        System.out.println("\nSelected:");
        pizza.displayPizza();

        String customize = getValidYesNo("Would you like to customize this signature item? (yes/no): ");
        if (customize.equalsIgnoreCase("yes")) {
            customizeSignaturePizza(pizza);
        }

        return pizza;
    }

    private void customizeSignaturePizza(Pizza pizza) {
        boolean customizing = true;

        while (customizing) {
            System.out.println("\n--- Customize Item ---");
            System.out.println("1) Add meat        2) Add cheese        3) Add regular topping");
            System.out.println("4) Remove meat     5) Remove cheese     6) Remove regular topping");
            System.out.println("0) Done");
            System.out.print("Choose an option: ");

            int userChoice = getValidInt();

            switch (userChoice) {
                case 1:
                    System.out.print("Meat to add: ");
                    pizza.addMeat(scanner.nextLine().trim());
                    break;
                case 2:
                    System.out.print("Cheese to add: ");
                    pizza.addCheese(scanner.nextLine().trim());
                    break;
                case 3:
                    System.out.print("Regular topping to add: ");
                    pizza.addRegularTopping(scanner.nextLine().trim());
                    break;
                case 4:
                    System.out.print("Meat to remove: ");
                    pizza.removeMeat(scanner.nextLine().trim());
                    break;
                case 5:
                    System.out.print("Cheese to remove: ");
                    pizza.removeCheese(scanner.nextLine().trim());
                    break;
                case 6:
                    System.out.print("Regular topping to remove: ");
                    pizza.removeRegularTopping(scanner.nextLine().trim());
                    break;
                case 0:
                    customizing = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }

            if (customizing) {
                System.out.println("\nUpdated item:");
                pizza.displayPizza();
            }
        }
    }

    private Drink createDrink() {
        System.out.println("\n=== ADD DRINK ===");
        String name = getValidString("Drink name: ");
        String size = getValidDrinkSize();
        return new Drink(name, size);
    }

    private Side createSide() {
        System.out.println("\n=== ADD SIDE ===");
        String name = getValidString("Add side selection (chips/garlic knots/etc.): ");
        return new Side(name);
    }

    private int getValidInt() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;

            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private int getValidNonNegativeInt() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= 0) return value;
                System.out.print("Please enter 0 or more: ");

            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private String getValidSize() {
        while (true) {
            System.out.print("Choose size (8, 12, 16): ");
            String size = scanner.nextLine().trim();
            if (size.equals("8") || size.equals("12") || size.equals("16")) return size;
            System.out.println("Invalid size. Please enter 8, 12, or 16.");
        }
    }

    private String getValidSpecialOption() {
        while (true) {
            System.out.print("Choose special option (toasted/stuffed crust/deep fried/none): ");
            String opt = scanner.nextLine().trim().toLowerCase();
            if (opt.equals("toasted") || opt.equals("stuffed crust") || opt.equals("deep fried") || opt.equals("none")) {
                return opt;
            }
            System.out.println("Invalid option. Choose toasted, stuffed crust, deep fried, or none.");
        }
    }

    private String getValidDrinkSize() {
        while (true) {

            System.out.print("Size (small, medium, large): ");
            String size = scanner.nextLine().trim().toLowerCase();
            if (size.equals("small") || size.equals("medium") || size.equals("large")) return size;
            System.out.println("Invalid size. Please enter small, medium, or large.");

        }
    }

    private String getValidYesNo(String prompt) {
        while (true) {

            System.out.print(prompt);
            String answer = scanner.nextLine().trim().toLowerCase();
            if (answer.equals("yes") || answer.equals("no")) return answer;
            System.out.println("Please enter yes or no.");

        }
    }

    private String getValidString(String prompt) {
        while (true) {

            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be blank.");

        }
    }
}