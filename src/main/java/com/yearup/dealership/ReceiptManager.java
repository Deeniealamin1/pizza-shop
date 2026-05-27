package com.yearup.dealership;
import java.util.Scanner;

public class Main
{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        boolean running = true;

        while(running)
        {
            System.out.println("\n=== PIZZA-licious ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice)
            {
                case 1:
                    createOrder();
                    break;

                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void createOrder()
    {
        Order order = new Order();
        boolean ordering = true;

        while(ordering)
        {
            System.out.println("\n=== ORDER SCREEN ===");
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Side");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice)
            {
                case 1:
                    Pizza pizza = createPizza();
                    order.addPizza(pizza);
                    break;

                case 2:
                    Drink drink = createDrink();
                    order.addDrink(drink);
                    break;

                case 3:
                    Side side = createSide();
                    order.addSide(side);
                    break;

                case 4:
                    order.displayOrder();
                    ReceiptManager.saveReceipt(order);
                    System.out.println("Order checked out successfully.");
                    ordering = false;
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

    public static Pizza createPizza()
    {
        System.out.println("\n=== CREATE PIZZA ===");

        System.out.print("Choose size (8, 12, 16): ");
        String size = scanner.nextLine();

        System.out.print("Choose crust (thin, regular, thick, cauliflower): ");
        String crust = scanner.nextLine();

        Pizza pizza = new Pizza(size, crust);

        System.out.print("Stuffed crust? (yes/no): ");
        String stuffed = scanner.nextLine();

        if(stuffed.equalsIgnoreCase("yes"))
        {
            pizza.setStuffedCrust(true);
        }

        System.out.print("Add meat (Leave blank if none): ");
        String meat = scanner.nextLine();
        if(!meat.isEmpty())
        {
            pizza.addMeat(meat);
        }

        System.out.print("Add cheese (Leave blank if none): ");
        String cheese = scanner.nextLine();
        if(!cheese.isEmpty())
        {
            pizza.addCheese(cheese);
        }

        System.out.print("Add topping (Leave blank if none): ");
        String topping = scanner.nextLine();
        if(!topping.isEmpty())
        {
            pizza.addTopping(topping);
        }

        System.out.print("Add sauce (Leave blank if none): ");
        String sauce = scanner.nextLine();
        if(!sauce.isEmpty())
        {
            pizza.addSauce(sauce);
        }

        return pizza;
    }

    public static Drink createDrink()
    {
        System.out.println("\n=== CREATE DRINK ===");

        System.out.print("Drink name: ");
        String name = scanner.nextLine();

        System.out.print("Drink size (small, medium, large): ");
        String size = scanner.nextLine();

        return new Drink(name, size);
    }

    public static Side createSide()
    {
        System.out.println("\n=== CREATE SIDE ===");

        System.out.print("Side name: ");
        String name = scanner.nextLine();

        return new Side(name);
    }
}