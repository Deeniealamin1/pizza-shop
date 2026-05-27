package com.yearup.dealership;

import java.util.ArrayList;

public class Pizza
{
    private String size;
    private String crust;
    private boolean stuffedCrust;

    private ArrayList<String> meats;
    private ArrayList<String> cheeses;
    private ArrayList<String> toppings;
    private ArrayList<String> sauces;

    public Pizza(String size, String crust)
    {
        this.size = size;
        this.crust = crust;

        meats = new ArrayList<>();
        cheeses = new ArrayList<>();
        toppings = new ArrayList<>();
        sauces = new ArrayList<>();
    }

    public void setStuffedCrust(boolean stuffedCrust)
    {
        this.stuffedCrust = stuffedCrust;
    }

    public void addMeat(String meat)
    {
        meats.add(meat);
    }

    public void addCheese(String cheese)
    {
        cheeses.add(cheese);
    }

    public void addTopping(String topping)
    {
        toppings.add(topping);
    }

    public void addSauce(String sauce)
    {
        sauces.add(sauce);
    }

    public double calculatePrice()
    {
        double total = 0;

        if(size.equals("8"))
        {
            total = 8.50;
            total += meats.size() * 1.00;
            total += cheeses.size() * 0.75;
        }
        else if(size.equals("12"))
        {
            total = 12.00;
            total += meats.size() * 2.00;
            total += cheeses.size() * 1.50;
        }
        else if(size.equals("16"))
        {
            total = 16.50;
            total += meats.size() * 3.00;
            total += cheeses.size() * 2.25;
        }

        if(stuffedCrust)
        {
            total += 2.00;
        }

        return total;
    }

    public void displayPizza()
    {
        System.out.println("\nPizza Details:");
        System.out.println("- Size: " + size + "\"");
        System.out.println("- Crust: " + crust);

        if(stuffedCrust)
        {
            System.out.println("- Stuffed Crust: Yes");
        }

        if(!meats.isEmpty()) System.out.println("- Meats: " + meats);
        if(!cheeses.isEmpty()) System.out.println("- Cheeses: " + cheeses);
        if(!toppings.isEmpty()) System.out.println("- Toppings: " + toppings);
        if(!sauces.isEmpty()) System.out.println("- Sauces: " + sauces);

        System.out.printf("  Item Price: $%.2f\n", calculatePrice());
    }
}