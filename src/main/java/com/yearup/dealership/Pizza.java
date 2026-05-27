package com.yearup.dealership;

import java.util.ArrayList;

public class Pizza extends MenuItem
{
    private final String size;
    private final String crust;
    private boolean stuffedCrust;

    private final ArrayList<String> meats    = new ArrayList<>();
    private final ArrayList<String> cheeses  = new ArrayList<>();
    private final ArrayList<String> toppings = new ArrayList<>();
    private final ArrayList<String> sauces   = new ArrayList<>();

    public Pizza(String size, String crust)
    {
        super("Custom Pizza");
        this.size  = (size  != null) ? size.trim()  : "8";
        this.crust = (crust != null) ? crust.trim() : "regular";
    }

    public void setStuffedCrust(boolean stuffedCrust) { this.stuffedCrust = stuffedCrust; }
    public void addMeat(String meat)      { if (meat    != null && !meat.isBlank())    meats.add(meat.trim());       }
    public void addCheese(String cheese)  { if (cheese  != null && !cheese.isBlank())  cheeses.add(cheese.trim());   }
    public void addTopping(String topping){ if (topping != null && !topping.isBlank()) toppings.add(topping.trim()); }
    public void addSauce(String sauce)    { if (sauce   != null && !sauce.isBlank())   sauces.add(sauce.trim());     }

    @Override
    public double calculatePrice()
    {
        double base;
        double meatRate;
        double cheeseRate;

        switch (size)
        {
            case "12":
                base = 12.00; meatRate = 2.00; cheeseRate = 1.50;
                break;
            case "16":
                base = 16.50; meatRate = 3.00; cheeseRate = 2.25;
                break;
            case "8":
            default:
                base = 8.50; meatRate = 1.00; cheeseRate = 0.75;
                break;
        }

        double total = base
                + meats.size()   * meatRate
                + cheeses.size() * cheeseRate;

        if (stuffedCrust) total += 2.00;

        return total;
    }

    public void displayPizza()
    {
        System.out.println("\nPizza Details:");
        System.out.println("- Size: " + size + "\"");
        System.out.println("- Crust: " + crust);
        if (stuffedCrust)           System.out.println("- Stuffed Crust: Yes");
        if (!meats.isEmpty())       System.out.println("- Meats: " + meats);
        if (!cheeses.isEmpty())     System.out.println("- Cheeses: " + cheeses);
        if (!toppings.isEmpty())    System.out.println("- Toppings: " + toppings);
        if (!sauces.isEmpty())      System.out.println("- Sauces: " + sauces);
        System.out.printf("  Item Price: $%.2f%n", calculatePrice());
    }
}