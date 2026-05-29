package com.pluralsight.models;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest
{
    @Test
    void testAddPizza()
    {
        Order order = new Order();
        Pizza pizza = new Pizza("12", "regular");

        order.addPizza(pizza);

        ArrayList<Pizza> pizzas = order.getPizzas();
        assertEquals(1, pizzas.size());
    }

    @Test
    void testAddDrink()
    {
        Order order = new Order();
        Drink drink = new Drink("Lemonade", "small");

        order.addDrink(drink);

        ArrayList<Drink> drinks = order.getDrinks();
        assertEquals(1, drinks.size());
    }

    @Test
    void testAddSide()
    {
        Order order = new Order();
        Side side = new Side("Garlic Knots");

        order.addSide(side);

        ArrayList<Side> sides = order.getSides();
        assertEquals(1, sides.size());
    }

    @Test
    void testGetTotal()
    {
        Order order = new Order();
        Pizza pizza = new Pizza("8", "regular");
        Drink drink = new Drink("Water", "small");
        Side side = new Side("Chips");

        order.addPizza(pizza);
        order.addDrink(drink);
        order.addSide(side);

        double total = order.getTotal();

        assertEquals(12.00, total);
    }

    @Test
    void testGetItems()
    {
        Order order = new Order();
        Side side = new Side("Fries");

        order.addSide(side);

        ArrayList<MenuItem> items = order.getItems();
        assertNotNull(items);
        assertEquals(1, items.size());
    }
}