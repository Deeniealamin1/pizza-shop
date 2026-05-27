package com.yearup.dealership;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckoutScreen
{
    private Scanner scanner;

    public CheckoutScreen()
    {
        this.scanner = new Scanner(System.in);
    }

    public boolean startCheckout(Order order)
    {
        System.out.println("\n=== CHECKOUT SCREEN ===");

        if (order.getPizzas().isEmpty() && order.getDrinks().isEmpty() && order.getSides().isEmpty())
        {
            System.out.println("Your cart is empty! Add items before checking out.");
            return false;
        }

        order.displayOrder();

        System.out.println("\n1) Confirm and Place Order");
        System.out.println("0) Cancel and Go Back to Menu");
        System.out.print("Choose an option: ");

        int choice = readInt();

        if (choice == 1)
        {
            ReceiptManager.saveReceipt(order);
            System.out.println("Thank you! Order processed successfully.");
            return true;
        }
        else
        {
            System.out.println("Returning to order menu...");
            return false;
        }
    }

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
                scanner.nextLine(); // flush bad input
                System.out.print("Please enter a number: ");
            }
        }
    }
}