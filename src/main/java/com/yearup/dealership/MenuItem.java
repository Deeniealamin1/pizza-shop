package com.yearup.dealership;

public abstract class MenuItem
{
    private final String name;

    public MenuItem(String name)
    {
        this.name = (name != null && !name.isBlank()) ? name : "Unknown Item";
    }

    public String getName()
    {
        return name;
    }

    public abstract double calculatePrice();
}