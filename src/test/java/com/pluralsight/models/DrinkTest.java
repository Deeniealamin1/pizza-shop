package com.pluralsight.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DrinkTest
{
    @Test
    public void testCalculatePriceSmall()
    {
        Drink drink = new Drink("Cola", "small");

        double price = drink.calculatePrice();

        assertEquals(2.00, price);
    }

    @Test
    public void testCalculatePriceMedium()
    {
        Drink drink = new Drink("Cola", "medium");

        double price = drink.calculatePrice();

        assertEquals(2.50, price);
    }

    @Test
    public void testCalculatePriceLarge()
    {
        Drink drink = new Drink("Cola", "large");

        double price = drink.calculatePrice();

        assertEquals(3.00, price);
    }

    @Test
    public void testToStringFormat()
    {
        Drink drink = new Drink("Root Beer", "medium");

        String result = drink.toString();

        assertNotNull(result);
        assertTrue(result.contains("medium"));
        assertTrue(result.contains("Root Beer"));
        assertTrue(result.contains("$2.50"));
    }
}