package com.pluralsight.models;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addPizza(Pizza pizza) { items.add(pizza); }
    public void addDrink(Drink drink) { items.add(drink); }
    public void addSide(Side side) { items.add(side); }

    public double getTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.calculatePrice();
        }
        return total;
    }

    public void displayOrder() {
        System.out.println("\n=== ORDER DETAILS ===");

        for (int i = items.size() - 1; i >= 0; i--) {
            MenuItem item = items.get(i);
            if (item instanceof Pizza) {
                ((Pizza) item).displayPizza();
            } else {
                System.out.println(item);
            }
        }

        System.out.printf("\nTotal: $%.2f\n", getTotal());
    }

    public ArrayList<MenuItem> getItems() { return items; }

    public ArrayList<Pizza> getPizzas() {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        for (MenuItem item : items) {
            if (item instanceof Pizza) pizzas.add((Pizza) item);
        }
        return pizzas;
    }

    public ArrayList<Drink> getDrinks() {
        ArrayList<Drink> drinks = new ArrayList<>();
        for (MenuItem item : items) {
            if (item instanceof Drink) drinks.add((Drink) item);
        }
        return drinks;
    }

    public ArrayList<Side> getSides() {
        ArrayList<Side> sides = new ArrayList<>();
        for (MenuItem item : items) {
            if (item instanceof Side) sides.add((Side) item);
        }
        return sides;
    }
}