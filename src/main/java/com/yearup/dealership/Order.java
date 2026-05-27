package com.yearup.dealership;

import java.util.ArrayList;

public class Order
{
    private final ArrayList<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item)
    {
        if (item != null) items.add(item);
    }

    public void addPizza(Pizza pizza)  { if (pizza != null) items.add(pizza); }
    public void addDrink(Drink drink)  { if (drink != null) items.add(drink); }
    public void addSide(Side side)     { if (side  != null) items.add(side);  }

    public double getTotal()
    {
        double total = 0;
        for (MenuItem item : items) total += item.calculatePrice();
        return total;
    }

    public void displayOrder()
    {
        System.out.println("\n=== ORDER DETAILS ===");
        if (items.isEmpty())
        {
            System.out.println("(no items)");
            return;
        }
        for (MenuItem item : items)
        {
            if (item instanceof Pizza) ((Pizza) item).displayPizza();
            else System.out.println(item);
        }
        System.out.printf("\nTotal: $%.2f%n", getTotal());
    }

    public ArrayList<MenuItem> getItems() { return items; }

    public ArrayList<Pizza> getPizzas()
    {
        ArrayList<Pizza> list = new ArrayList<>();
        for (MenuItem item : items) if (item instanceof Pizza) list.add((Pizza) item);
        return list;
    }

    public ArrayList<Drink> getDrinks()
    {
        ArrayList<Drink> list = new ArrayList<>();
        for (MenuItem item : items) if (item instanceof Drink) list.add((Drink) item);
        return list;
    }

    public ArrayList<Side> getSides()
    {
        ArrayList<Side> list = new ArrayList<>();
        for (MenuItem item : items) if (item instanceof Side) list.add((Side) item);
        return list;
    }
}