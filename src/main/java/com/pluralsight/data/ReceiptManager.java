package com.pluralsight.data;

import com.pluralsight.models.MenuItem;
import com.pluralsight.models.Order;
import com.pluralsight.models.Pizza;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager{

    public static void saveReceipt(Order order) {

        if (order == null) {
            System.out.println("Cannot save receipt: order is null.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy  hh:mm:ss a");

        String fileName = now.format(fileFormatter) + ".txt";

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));

            writer.write("=============================");
            writer.newLine();
            writer.write("       FOOD SHOP RECEIPT     ");
            writer.newLine();
            writer.write("=============================");
            writer.newLine();
            writer.write("Date: " + now.format(displayFormatter));
            writer.newLine();
            writer.newLine();
            writer.write("--- ITEMS ---");
            writer.newLine();

            for (MenuItem item : order.getItems()) {
                if (item instanceof Pizza) {
                    Pizza pizza = (Pizza) item;
                    writer.write("  " + pizza.getName() + " (" + pizza.getSize() + "\" [" + pizza.getCrustType() + "])");
                    writer.newLine();
                    writer.write("    Special Option: " + pizza.getSpecialOption());
                    writer.newLine();

                    for (String meat : pizza.getMeats()) {
                        writer.write("    + Meat:    " + meat);
                        writer.newLine();
                    }
                    for (String cheese : pizza.getCheeses()) {
                        writer.write("    + Cheese:  " + cheese);
                        writer.newLine();
                    }
                    for (String topping : pizza.getRegularToppings()) {
                        writer.write("    + Regular Topping: " + topping);
                        writer.newLine();
                    }

                    writer.write(String.format("    Price: $%.2f", pizza.calculatePrice()));
                    writer.newLine();
                } else {
                    writer.write("  " + item.toString());
                    writer.newLine();
                }
            }

            writer.newLine();
            writer.write("=============================");
            writer.newLine();
            writer.write(String.format("  TOTAL:  $%.2f", order.getTotal()));
            writer.newLine();
            writer.write("=============================");
            writer.newLine();

            System.out.println("Receipt saved to: " + fileName);

        } catch (IOException e) {

            System.out.println("Error saving receipt: " + e.getMessage());

        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file writer: " + e.getMessage());

            }
        }
    }

    private static void writePizza(BufferedWriter writer, Pizza pizza) throws IOException {
        writer.write("  " + pizza.getName()
                + " (" + pizza.getSize() + "\" [" + pizza.getCrustType() + "])");
        writer.newLine();

        writer.write("    Special Option: " + pizza.getSpecialOption());
        writer.newLine();

        for (String meat : pizza.getMeats()) {
            writer.write("    + Meat:    " + meat);
            writer.newLine();
        }
        for (String cheese : pizza.getCheeses()) {
            writer.write("    + Cheese:  " + cheese);
            writer.newLine();
        }
        for (String topping : pizza.getRegularToppings()) {
            writer.write("    + Regular Topping: " + topping);
            writer.newLine(); }

        writer.write(String.format("    Price: $%.2f", pizza.calculatePrice()));
        writer.newLine();
    }
}