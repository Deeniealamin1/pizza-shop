package com.pluralsight.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignaturePizzaTest
{
    @Test
    void testMargheritaPreset()
    {
        SignaturePizza pizza = SignaturePizza.margherita();

        assertEquals("Signature Margherita", pizza.getName());
        assertEquals("12", pizza.getSize());
        assertEquals("toasted", pizza.getSpecialOption());

        assertTrue(pizza.getCheeses().contains("Mozzarella"));
        assertTrue(pizza.getRegularToppings().contains("Tomatoes"));
    }

    @Test
    void testVeggiePreset()
    {
        SignaturePizza pizza = SignaturePizza.veggie();

        assertEquals("Signature Veggie", pizza.getName());
        assertEquals("8", pizza.getSize());

        assertTrue(pizza.getRegularToppings().contains("Bell Peppers"));
        assertTrue(pizza.getCheeses().contains("Mozzarella"));
    }
}