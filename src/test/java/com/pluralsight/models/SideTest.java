package com.pluralsight.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SideTest
{
    @Test
    void testCalculatePrice()
    {
        Side side = new Side("Garlic Knots");

        double price = side.calculatePrice();

        assertEquals(1.50, price);
    }

    @Test
    void testToStringFormat()
    {
        Side side = new Side("Chips");

        String result = side.toString();

        assertNotNull(result);
        assertTrue(result.contains("Chips"));
        assertTrue(result.contains("$1.50"));
    }
}