package com.pluralsight.ui;

import com.pluralsight.data.ReceiptManager;
import com.pluralsight.models.Order;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckoutScreen {
    private Scanner scanner;

    public CheckoutScreen() {
        this.scanner = new Scanner(System.in);
    }

    public boolean startCheckout(Order order) {
        System.out.println("\n=== CHECKOUT SCREEN ===");

        // Enforces checklist rule: Minimum 1 item or drink/side required
        if (order.getItems().isEmpty()) {
            System.out.println("Your cart is empty! Add items before checking out.");
            return false;
        }

        order.displayOrder();

        System.out.println("\n1) Confirm and Place Order");
        System.out.println("0) Cancel and Go Back to Menu");

        int userChoice = getValidChoice(0, 1);

        if (userChoice == 1) {
            ReceiptManager.saveReceipt(order);
            System.out.println("Thank you! Order processed successfully.");
            return true;
        } else {
            System.out.println("Returning to order menu...");
            return false;
        }
    }

    private int getValidChoice(int min, int max) {
        while (true) {
            System.out.print("Choose an option: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= min && choice <= max) return choice;
                System.out.println("Please enter " + min + " or " + max + ".");
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}