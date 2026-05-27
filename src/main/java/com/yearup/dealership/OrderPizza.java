package com.yearup.dealership;

import java.util.ArrayList;

public class Order
{
    private ArrayList<Pizza> pizzas;
    private ArrayList<Drink> drinks;
    private ArrayList<Side> sides;

    public Order()
    {
        pizzas = new ArrayList<>();
        drinks = new ArrayList<>();
        sides = new ArrayList<>();
    }

    public void addPizza(Pizza pizza)
    {
        pizzas.add(pizza);
    }

    public void addDrink(Drink drink)
    {
        drinks.add(drink);
    }

    public void addSide(Side side)
    {
        sides.add(side);
    }

    public double getTotal()
    {
        double total = 0;

        for(Pizza pizza : pizzas)
        {
            total += pizza.calculatePrice();
        }

        for(Drink drink : drinks)
        {
            total += drink.calculatePrice();
        }

        for(Side side : sides)
        {
            total += side.calculatePrice();
        }

        return total;
    }

    public void displayOrder()
    {
        System.out.println("\n=== ORDER DETAILS ===");

        for(Pizza pizza : pizzas)
        {
            pizza.displayPizza();
        }

        for(Drink drink : drinks)
        {
            System.out.println(drink);
        }

        for(Side side : sides)
        {
            System.out.println(side);
        }

        System.out.printf("\nTotal: $%.2f\n", getTotal());
    }

    public ArrayList<Pizza> getPizzas()
    {
        return pizzas;
    }

    public ArrayList<Drink> getDrinks()
    {
        return drinks;
    }

    public ArrayList<Side> getSides()
    {
        return sides;
    }
}