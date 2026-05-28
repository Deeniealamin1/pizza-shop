package com.pluralsight.models;

import java.util.ArrayList;

public class Pizza extends MenuItem {
    private String size;
    private String crustType;       // Aligned to checklist rule: bread/crust/shell/custom type
    private String specialOption;   // Aligned to checklist rule: toasted/stuffed crust/deep fried
    private ArrayList<String> meats;
    private ArrayList<String> cheeses;
    private ArrayList<String> regularToppings; // Aligned to checklist rule: Regular toppings free

    public Pizza(String size, String crustType) {
        super("Custom Order Item");
        this.size = size;
        this.crustType = crustType;
        this.specialOption = "none";
        meats = new ArrayList<>();
        cheeses = new ArrayList<>();
        regularToppings = new ArrayList<>();
    }

    protected Pizza(String name, String size, String crustType) {
        super(name);
        this.size = size;
        this.crustType = crustType;
        this.specialOption = "none";
        meats = new ArrayList<>();
        cheeses = new ArrayList<>();
        regularToppings = new ArrayList<>();
    }

    public void setSpecialOption(String specialOption) {
        this.specialOption = specialOption;
    }

    public void addMeat(String meat) {
        if (!meat.isEmpty()) meats.add(meat);
    }

    public void addCheese(String cheese) {
        if (!cheese.isEmpty()) cheeses.add(cheese);
    }

    public void addRegularTopping(String topping) {
        if (!topping.isEmpty()) regularToppings.add(topping);
    }

    public void removeMeat(String meat) { meats.remove(meat); }
    public void removeCheese(String cheese) { cheeses.remove(cheese); }
    public void removeRegularTopping(String topping) { regularToppings.remove(topping); }

    public String getSize() { return size; }
    public String getCrustType() { return crustType; }
    public String getSpecialOption() { return specialOption; }

    // Fixed: Added getters so ReceiptManager compiles perfectly without errors
    public ArrayList<String> getMeats() { return meats; }
    public ArrayList<String> getCheeses() { return cheeses; }
    public ArrayList<String> getRegularToppings() { return regularToppings; }

    @Override
    public double calculatePrice() {
        double basePrice = 0;
        double meatPrice = 0;
        double cheesePrice = 0;

        if (size.equals("8")) {
            basePrice = 8.50;
            meatPrice = meats.size() * 1.00;
            cheesePrice = cheeses.size() * 0.75;
        } else if (size.equals("12")) {
            basePrice = 12.00;
            meatPrice = meats.size() * 2.00;
            cheesePrice = cheeses.size() * 1.50;
        } else if (size.equals("16")) {
            basePrice = 16.50;
            meatPrice = meats.size() * 3.00;
            cheesePrice = cheeses.size() * 2.25;
        }

        double total = basePrice + meatPrice + cheesePrice;

        if (!specialOption.equalsIgnoreCase("none")) {
            total += 2.00;
        }

        return total;
    }

    public void displayPizza() {
        System.out.println("\n" + getName() + " Details:");
        System.out.println("- Size: " + size + "\"");
        System.out.println("- Type (bread/crust/shell/custom): " + crustType);
        System.out.println("- Special Option (toasted/stuffed crust/deep fried): " + specialOption);

        if (!meats.isEmpty()) System.out.println("- Premium Meats: " + meats);
        if (!cheeses.isEmpty()) System.out.println("- Premium Cheeses: " + cheeses);
        if (!regularToppings.isEmpty()) System.out.println("- Regular Toppings: " + regularToppings);

        System.out.printf("  Item Price: $%.2f\n", calculatePrice());
    }
}