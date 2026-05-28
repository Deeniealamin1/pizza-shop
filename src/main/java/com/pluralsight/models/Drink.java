package com.pluralsight.models;

public class Drink extends MenuItem {
    private String size;

    public Drink(String name, String size) {
        super(name);
        this.size = size;
    }

    @Override
    public double calculatePrice() {
        if (size.equalsIgnoreCase("small")) {
            return 2.00;
        } else if (size.equalsIgnoreCase("medium")) {
            return 2.50;
        } else {
            return 3.00;
        }
    }

    @Override
    public String toString() {
        return size + " " + getName() + " - $" + String.format("%.2f", calculatePrice());
    }
}