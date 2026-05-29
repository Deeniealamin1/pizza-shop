package com.pluralsight.data;

import com.pluralsight.models.Order;
import com.pluralsight.models.Pizza;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReceiptManagerTest
{
    @Test
    public void testSaveReceipt()
    {
        Order order = new Order();
        Pizza pizza = new Pizza("12", "Thin Crust");
        order.addPizza(pizza);

        ReceiptManager.saveReceipt(order);

        assertNotNull(order);
    }

    @Test
    public void testSaveReceiptNull()
    {
        ReceiptManager.saveReceipt(null);

        assertTrue(true);
    }

    @Test
    public void testWritePizza()
    {
        Pizza pizza = new Pizza("14", "Hand Tossed");
        pizza.addMeat("Pepperoni");
        pizza.addCheese("Mozzarella");
        pizza.addRegularTopping("Mushrooms");

        CharArrayWriter charWriter = new CharArrayWriter();
        BufferedWriter writer = new BufferedWriter(charWriter);

        try
        {
            ReceiptManager.writePizza(writer, pizza);
            writer.flush();
        }
        catch (IOException e)
        {
            System.out.println("Error running test write pizza.");
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                System.out.println("Error closing test writer.");
            }
        }

        String output = charWriter.toString();
        assertTrue(output.contains("14\""));
    }
}