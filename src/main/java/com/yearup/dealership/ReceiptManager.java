package com.yearup.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptManager
{
    private static final String RECEIPTS_DIR = "receipts";

    public static void saveReceipt(Order order)
    {
        if (order == null)
        {
            System.out.println("Cannot save receipt: order is null.");
            return;
        }

        // Auto-create the receipts folder if it doesn't exist
        try
        {
            Files.createDirectories(Paths.get(RECEIPTS_DIR));
        }
        catch (IOException e)
        {
            System.out.println("Could not create receipts folder: " + e.getMessage());
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String fileName = RECEIPTS_DIR + "/" + LocalDateTime.now().format(formatter) + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName)))
        {
            writer.write("PIZZA-licious Receipt");
            writer.newLine();
            writer.write("=====================");
            writer.newLine();
            writer.newLine();

            for (MenuItem item : order.getItems())
            {
                if (item instanceof Pizza)
                {
                    writer.write("Pizza");
                    writer.newLine();
                    writer.write("-----------------");
                    writer.newLine();
                    writer.write("Price: $" + String.format("%.2f", item.calculatePrice()));
                    writer.newLine();
                    writer.newLine();
                }
                else
                {
                    writer.write("- " + item);
                    writer.newLine();
                }
            }

            writer.write("=====================");
            writer.newLine();
            writer.write("Total: $" + String.format("%.2f", order.getTotal()));
            writer.newLine();

            System.out.println("Receipt saved to: " + fileName);
        }
        catch (IOException e)
        {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}